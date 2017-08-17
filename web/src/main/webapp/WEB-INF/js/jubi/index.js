$(function () {
    $("#coinSel").off("change").on("change", function () {
        var coin = $(this).val();
        window.location.href = ctx + "/page/coin/order/info.html?coin=" + coin;
    })
    renderTickers()
});

var ctx = $("#ctx").val();

/**
 * 查询所有币当前行情
 */
function queryAllCoinTickers() {
    var url = ctx + "/ticker/coins/recent?t=" + Math.random();
    $.getJSON(url, function (json) {
        if (json.status != '200') {
            alert(json.message);
            return
        }
        var ds = buildTickers(json);
        renderTickers(ds)
    })
}

function buildTickers(json) {
    var datas = json.data
    if (!datas || datas.length == 0) {
        return [];
    }
    var ds = []
    $.each(datas, function (index, data) {
        var price = data.last;
        var beginPrice = data.beginPrice;
        var threePrice = data.threePrice;
        var sevenPrice = data.sevenPrice;
        var fiftyPrice = data.fiftyPrice;
        var monthPrice = data.monthPrice;
        var beginRate = !beginPrice ? 0 : ((price - beginPrice) / beginPrice * 100).toFixed(2) + "%"
        var threeRate = !threePrice ? 0 : ((price - threePrice) / threePrice * 100).toFixed(2) + "%"
        var sevenRate = !sevenPrice ? 0 : ((price - sevenPrice) / sevenPrice * 100).toFixed(2) + "%"
        var fiftyRate = !sevenPrice ? 0 : ((price - fiftyPrice) / sevenPrice * 100).toFixed(2) + "%"
        var monthRate = !sevenPrice ? 0 : ((price - monthPrice) / sevenPrice * 100).toFixed(2) + "%"

        var d = [data.name + ' ' + data.coin.toUpperCase(), data.last, data.low, data.high, data.vol, data.volume,
            beginRate, threeRate, sevenRate, fiftyRate, monthRate]
        ds.push(d)
    })
    return ds
}

function renderTickers() {
    var cols = ['名称', '当前价格', '最低价', '最高价', '交易量', '交易额',
        '涨幅', '三日涨幅', "七日涨幅", "半月涨幅", "月涨幅"]

    var cs = [];
    $.each(cols, function (index, col) {
        cs.push({title: col})
    });

    var url = ctx + "/ticker/coins/recent?t=" + Math.random();
    var table = $('#tickerMainTable').DataTable({
        "ajax": {
            "url": url,
            "dataSrc": buildTickers
        },
        columns: cs,
        autoWidth: false,
        info: false,
        paging: false,
        scrollY: 600
    });

    setInterval(function () {
        table.ajax.reload()
    }, 10000);
}