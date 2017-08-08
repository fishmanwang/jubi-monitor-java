$(function () {
    var t = new Date().getTime();
    var s = formatDateTime4(new Date(t));
    $("#timeInput").val(s);

    $("#coinSel").off("change").on("change", function () {
        queryCoinDepth();
    });

    $('#timeInput').off("keydown").on("keydown", function (e) {
        if (e.keyCode == 13) {
            queryCoinDepth();
        }
    });

});

var queryCoinDepth = function () {
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
    var url = ctx + "/depth/" + coin + "?time=" + time;
    $.get(url, function (json) {
        if (json.status == 200) {
            var ds = json.data;
            render(buildShowData(ds));
        } else {
            alert(json.message);
        }
    });
}