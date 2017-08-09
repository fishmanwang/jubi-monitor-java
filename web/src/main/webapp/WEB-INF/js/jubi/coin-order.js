$(function () {
    var t = new Date().getTime();
    var s = formatDateTime(new Date(t));
    $("#timeInput").val(s);

    $("#coinSel").off("change").on("change", function () {
        queryCoinOrders();
    });

    $('#timeInput').off("keydown").on("keydown", function (e) {
        if (e.keyCode == 13) {
            queryCoinOrders();
        }
    });

    queryCoinOrders()
});

var ctx = $("#ctx").val();

function render(datas) {
    $("#mainDiv").html("")
    if (!datas || datas.length == 0) {
        return;
    }
    var c = "<table>";
    c += "<tr><td>价格</td><td>数量</td><td>总金额</td><td>交易时间</td></tr>"
    $.each(datas, function (index, data) {
        c += "<tr>"
        c += "<td>" + data.price + "</td>"
        c += "<td>" + data.amount + "</td>"
        c += "<td>" + data.total + "</td>"
        c += "<td>" + data.tradeTime + "</td>"
        c += "</tr>";
    });
    c += "</table>";
}

var queryCoinOrders = function () {
    var coin = $("#coinSel").val();
    if (!coin) {
        alert("请选择币");
        return;
    }
    var time = $("#timeInput").val();
    if (!time) {
        alert("请设置时间")
        return;
    }
    var url = ctx + "/coin/order/" + coin + "?time=" + time;
    $.get(url, function (json) {
        if (json.status == 200) {
            var ds = json.data;

        } else {
            alert(json.message);
        }
    });
}