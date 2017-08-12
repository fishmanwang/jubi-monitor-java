$(function () {
    $("#coinSel").off("change").on("change", function () {
        var coin = $(this).val();
        window.location.href = ctx + "/page/coin/order/info.html?coin=" + coin;
    })
});

var ctx = $("#ctx").val();