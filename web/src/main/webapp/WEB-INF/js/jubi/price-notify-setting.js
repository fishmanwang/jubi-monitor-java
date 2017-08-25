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
            var price = $(this).val()
            if (isNaN(price)) {
                alert("必须输入数字")
                return;
            }
            if (!price) {
                alert("价格不能等于0");
                return;
            }
            var coin = $(this).attr("coin");
            var prices = $(".pricesSpan[coin='" + coin + "']").data("prices");
            if (prices.indexOf(price) > -1) {
                alert("价格已存在");
            } else {
                prices.push(price)
                prices.sort();
                renderCoinPrices(coin, prices);
            }
            $(this).val("");
        }
    });

    $("#saveBtn").off("click").on("click", function () {
        var ss = $(".pricesSpan").toArray();
        var data = [];
        ss.forEach(function (s) {
            var coin = $(s).attr("coin");
            var prices = $(s).data("prices");
            data.push({coin: coin, prices: prices})
        });
        $.ajax({
            type: "POST",
            url: ctx + "/notify/price/user/save",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (json) {
                console.log(json)
                if (json.status == 200) {
                    alert("保存成功")
                } else {
                    alert(json.message)
                }
            },
            error: function (message) {
                alert("提交数据失败！");
            }
        });
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
        $(".pricesSpan[coin='" + coin + "']").data("prices", prices)
        renderCoinPrices(coin, prices);
    });
}