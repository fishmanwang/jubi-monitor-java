$(function () {
    var t = new Date().getTime();
    var s = formatDateTime4(new Date(t));
    $("#timeInput").val(s);

    $('#timeInput').keydown(function (e) {
        if (e.keyCode == 13) {
            queryCurrentDepth();
        }
    });

    queryCurrentDepth()

});

var ctx = $("#ctx").val();
var spans = [0.03, 0.05, 0.08, 0.1, 0.15, 0.2];

var queryCurrentDepth = function () {
    var time = $("#timeInput").val();
    var url = ctx + "/depth/query?time=" + time;
    $.get(url, function (json) {
        if (json.status == 200) {
            var ds = json.data;
            render(buildShowData(ds));
        } else {
            alert(json.message);
        }
    });
}

function render(ds) {
    var cols = ds.cols;
    var vals = ds.vals;

    var cs = [];
    $.each(cols, function (index, col) {
        cs.push({title: col});
    });

    $("#mainDiv").html('<table id="main" class="display" width="100%"></table>');

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
        cols.push(formatDecimal('-', span));
        cols.push("比率 (" + formatDecimal('', span) + ")");
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


        var plus = buildPlusSpanData(price, asks);
        var minus = buildMinusSpanData(price, bids);
        for (var i = 0; i < spans.length; i++) {
            var p = plus[i];
            var m = minus[i];
            d.push(p);
            d.push(m);
            if (p == 0 || m == 0) {
                d.push(0);
            } else {
                d.push((m / p).toFixed(2));
            }
        }

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
        amounts.push(parseInt(total));
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
        amounts.push(parseInt(total));
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