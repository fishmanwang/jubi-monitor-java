$(function () {
    bindBtnEvents()

    $(".settingDiv[setted=true]").show();
});

var ctx = $("#ctx").val();

function bindBtnEvents() {

    $("#coinSel").off("change").on("change", function () {
        var coin = $(this).val();
        if (!coin) {
            return;
        }
        if ($(".settingDiv[coin='" + coin + "']:visible").length > 0) {
            return;
        }
        if ($(".settingDiv:visible").length >= 3) {
            alert("监控的虚拟币不能大于3个")
            $(this).val("")
            return;
        }
        $(".settingDiv[coin='" + coin + "']").show();
    });

    $(".spanInput").off("keyup").on("keyup", function () {
        var span = $(this).val();
        if (!span) {
            return;
        }
        if (isNaN(span)) {
            alert("请输入1-60的整数");
            span = truncateInput(span);
            $(this).val(span)
            return
        }
        span = parseInt(span)
        if (span < 1 || span > 60) {
            alert("请输入1-60的整数");
            span = truncateInput(span);
            $(this).val(span)
            return
        }
        $(this).val(span)
    });

    $(".rateInput").off("keyup").on("keyup", function () {
        var rate = $(this).val();
        if (!rate) {
            return;
        }
        if (isNaN(rate)) {
            alert("请输入1-100的数字（一位小数）");
            rate = truncateInput(rate);
            $(this).val(rate)
            return
        }
        rate = parseFloat(rate)
        if (rate < 1 || rate > 100) {
            alert("请输入1-100的数字（一位小数）");
            rate = truncateInput(rate);
            $(this).val(rate)
            return
        }
        var index = (rate + "").indexOf('.')
        if (index != -1) {
            if ((rate + "").length - index - 1 > 2) {
                $(this).val(rate.toFixed(2))
            }
        }
    });

    $(".delSetting").off("click").on("click", function () {
        var coin = $(this).attr("coin");
        $(".settingDiv[coin='" + coin + "']").hide()
    });

    $("#saveBtn").off("click").on("click", function () {
        var items = $(".settingDiv:visible");
        var datas = [];
        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            var coin = $(item).attr("coin");
            var span = $(item).find(".spanInput").val();
            if (!span) {
                alert("请完善信息");
                return;
            }
            var rate = $(item).find(".rateInput").val();
            if (!rate) {
                alert("请完善信息");
                return;
            }
            datas.push({coin: coin, span: span, rate: rate});
        }
        $.ajax({
            type: "POST",
            url: ctx + "/notify/wave/save",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(datas),
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
    });
}

function truncateInput(val) {
    val = val + "";
    val = val.substr(0, val.length - 1);
    return val;
}