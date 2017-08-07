$(function () {

    queryCurrentDepth()

});

var ctx = $("#ctx").val();
var spans = [0.03, 0.05, 0.08, 0.1, 0.15, 0.2]

var queryCurrentDepth = function () {
    var url = ctx + "/depth/current"
    $.get(url, function (json) {
        if (json.status == 200) {
            var ds = json.data;
            console.log(ds)
            render(buildShowData(ds))
        } else {
            alert(json.message)
        }
    });
}

function render(ds) {
    $("#content").html("");

    var html = "<table width='100%'>";
    html += "<tr>";
    var cols = ds.cols;
    $.each(cols, function (index, col) {
        html += "<td>" + col + "</td>"
    });
    html += "</tr>";
    var vals = ds.vals;
    $.each(vals, function (index, ds) {
        html += "<tr>";
        $.each(ds, function (index, d) {
            html += "<td>" + d + "</td>"
        });
        html += "</tr>"
    });

    html += "</table>";

    //$("#content").html(html);

    var cs = [];
    $.each(cols, function (index, col) {
        cs.push({title: col});
    });
    console.log(cs)
    console.log(typeof(vals))
    console.log(vals)
    $('#main').DataTable({
        data: vals,
        columns: cs
    });
}

/**
 * 构建用于展示的数据
 * @param ds
 */
function buildShowData(datas) {

    var cols = ['名称', '时间', '价格'];
    $.each(spans, function (index, span) {
        cols.push(formatDecimal('+', span));
    });

    $.each(spans, function (index, span) {
        cols.push(formatDecimal('-', span));
    });

    var vals = [];
    var ds = {
        cols: cols,
        vals: vals
    };
    $.each(datas, function (index, item) {
        var coin = item.coin;
        var pk = item.pk;
        var price = item.price;
        var asks = item.asks;
        var bids = item.bids;

        var d = [];
        d.push(coin);
        d.push(pk);
        d.push(price)


        d = d.concat(buildPlusSpanData(price, asks));
        d = d.concat(buildMinusSpanData(price, bids));

        vals.push(d)
    });

    console.log(ds);

    return ds;
}

function buildPlusSpanData(price, datas) {
    datas = eval(datas)
    var max = datas[0][0];
    var amounts = [];
    $.each(spans, function (index, span) {
        var sprice = price * (1 + span);
        if (max < sprice) {
            amounts.push(0);
            return;
        }
        var total = 0;
        $.each(datas, function (index, data) {
            var p = data[0];
            var vol = data[1];
            if (p <= sprice) {
                total += p * vol;
            }
        });
        amounts.push(total.toFixed(2));
    });
    return amounts;
}

function buildMinusSpanData(price, datas) {
    datas = eval(datas)
    var min = datas[datas.length - 1][0];
    var amounts = [];
    $.each(spans, function (index, span) {
        var sprice = price * (1 - span);
        if (min > sprice) {
            amounts.push(0);
            return;
        }
        var total = 0;
        $.each(datas, function (index, data) {
            var p = data[0];
            var vol = data[1];
            if (p >= sprice) {
                total += p * vol;
            }
        });
        amounts.push(total.toFixed(2));
    });
    return amounts;
}

/**
 * 将小数格式化为百分数 0.01 -> 1%
 * @param d
 */
function formatDecimal(prefix, d) {
    return prefix + d * 100 + "%";
}