$(function () {

    bindBtnEvents();

    getUserCoinNotifyPrice();
});

var ctx = $("#ctx").val();

function getUserCoinNotifyPrice() {
    var url = ctx + "/notify/price/user";
    $.getJSON(url, function (json) {
        if (json.status != 200) {
            alert(json.message);
            return;
        }
        var ds = json.data;
        ds.forEach(function (data) {
            var prices = data.prices.sort();
            renderCoinPrices(data.coin, prices)
        })
    });
}

function renderCoinPrices(coin, prices) {
    var html = "";
    prices.forEach(function (price) {
        html += "<span>" + price + "&nbsp;<span class='delPriceSpan' coin='" + coin + "' price='" + price + "'>x</span></span>";
    });
    $(".pricesSpan[coin='" + coin + "']").data("prices", prices)
    $(".pricesSpan[coin='" + coin + "']").html(html)
}

function bindBtnEvents() {
    $(".nofityAddBtn").off("keydown").on("keydown", function (e) {
        if (e.keyCode == 13) {
            alert($(this).val())
        }
    });

    $("#main").off("click", ".delPriceSpan").on("click", ".delPriceSpan", function () {
        var coin = $(this).attr("coin");
        var price = $(this).attr("price");
        var prices = $(".pricesSpan[coin='" + coin + "']").data("prices")
        for (var i = 0; i < prices.length; i++) {
            if (prices[i] == price) {
                prices.splice(i, 1);
            }
        }
        renderCoinPrices(coin, prices);
    });
}