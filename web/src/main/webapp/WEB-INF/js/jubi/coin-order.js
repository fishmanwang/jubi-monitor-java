$(function () {
    var t = new Date().getTime();
    var s = formatDateTime(new Date(t));
    $("#timeInput").val(s);

    $("#coinSel").off("change").on("change", function () {
        queryCoinOrders();
    });
    
});

var ctx = $("#ctx").val();

function render(datas) {
    $("#mainDiv").html("");
    if (!datas || datas.length == 0) {
        return;
    }
    var c = "<table with='600'>";
    c += "<tr><td width='20%'>价格</td><td width='20%'>数量</td><td width='20%'>总金额</td><td>交易时间</td></tr>";
    $.each(datas, function (index, data) {
        c += "<tr>";
        c += "<td>" + data.price + "</td>";
        c += "<td>" + data.amount.toFixed(2) + "</td>";
        c += "<td>" + (data.price * data.amount).toFixed(2) + "</td>";
        c += "<td>" + data.tradeTime + "</td>";
        c += "</tr>";
    });
    c += "</table>";
    $("#mainDiv").html(c);
}

var queryCoinOrders = function () {
    var coin = $("#coinSel").val();
    if (!coin) {
        alert("请选择币");
        return;
    }
    var url = ctx + "/coin/order/" + coin;
    $.get(url, function (json) {
        if (json.status == 200) {
            var ds = json.data;
            render(ds)
        } else {
            alert(json.message);
        }
    });
}