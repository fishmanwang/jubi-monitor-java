$(function () {
    bindBtnEvents()
});

var ctx = $("#ctx").val();

function bindBtnEvents() {

    $("#saveBtn").off("click").on("click", function() {
        var ds = []
        var ss = $(".setting")
        for (var i=0; i<ss.length; i++) {
            var s = $(ss[i])
            var coin = s.attr("coin");
            var rate = s.find(".rateSelect").val()
            if (rate > 0) {
                ds.push({coin: coin, rate: rate})
            }
        }
        $.ajax({
            type: "POST",
            url: ctx + "/notify/rate/save",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(ds),
            dataType: "json",
            success: function (json) {
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
    })

}