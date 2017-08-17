$(function () {
    $("#coinSel").off("change").on("change", function () {
        var coin = $(this).val();
        window.location.href = ctx + "/page/coin/order/info.html?coin=" + coin;
    })
    
    queryAllCoinTickers()
    setInterval(queryAllCoinTickers, 10000);
    //clearInterval(sh);
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
        var ds = buildTickers(json.data);
        renderTickers(ds)
    })
}

function buildTickers(datas) {
    if (!datas || datas.length == 0) {
        return;
    }
    var ds = []
    $.each(datas, function (index, data) {
        var price = data.last;
        var beginPrice = data.beginPrice;
        var threePrice = data.threePrice;
        var sevenPrice = data.sevenPrice;
        var beginRate = !beginPrice ? 0 : ((price - beginPrice) / beginPrice * 100).toFixed(2) + "%"
        var threeRate = !threePrice ? 0 : ((price - threePrice) / threePrice * 100).toFixed(2) + "%"
        var sevenRate = !sevenPrice ? 0 : ((price - sevenPrice) / sevenPrice * 100).toFixed(2) + "%"

        var d = [data.name, data.last, data.low, data.high, data.vol, data.volume, beginRate, threeRate, sevenRate]
        ds.push(d)
    })
    return ds
}

function renderTickers(datas) {
    var cols = ['名称', '当前价格', '最低价', '最高价', '交易量', '交易额', '涨幅', '三日涨幅', "七日涨幅"]

    var cs = [];
    $.each(cols, function (index, col) {
        cs.push({title: col});
    });

    $("#tickerMainDiv").html('<table id="tickerMainTable" class="display" width="100%"></table>');
    $('#tickerMainTable').DataTable({
        data: datas,
        columns: cs,
        autoWidth: false,
        info: false,
        paging: false,
        scrollY: 600
    });
}