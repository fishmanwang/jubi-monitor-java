$(function () {

    getFavoriteCoins();
    bindBtnEvents();

});

var ctx = $("#ctx").val();

function bindBtnEvents() {
    $("#baseCommitBtn").off("click").on("click", function () {
        saveAccount();
    });

    $("#saveFcoinsBtn").off("click").on("click", function () {
        setFavoriteCoins();
    });
}

function saveAccount() {
    var nickname = $("#nicknameInput").val().trim();
    var phone = $("#phoneInput").val().trim();
    var email = $("#emailInput").val().trim();

    var data = {
        nickname: nickname,
        phone: phone,
        email: email
    }

    $.ajax({
        type: "POST",
        url: ctx + "/account/save",
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
}

function getFavoriteCoins() {
    $.getJSON(ctx + "/account/fcoin/get", function (json) {
        if (json.status != 200) {
            alert(json.message);
            return;
        }
        var ds = json.data;
        $.each(ds, function (index, d) {
            $(".coinChk[code='" + d.coin + "']").attr("checked", "checked");
        });
    });
}

function setFavoriteCoins() {
    var datas = [];
    var fcs = $(".coinChk:checked");
    if (fcs.length > 10) {
        alert("最多关注10个虚拟币");
        return;
    }
    $.each(fcs, function (index, item) {
        var coin = $(item).val();
        datas.push({coin: coin, priority: 0})
    });
    console.log(datas)
    $.ajax({
        type: "POST",
        url: ctx + "/account/fcoin/set",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(datas),
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
}