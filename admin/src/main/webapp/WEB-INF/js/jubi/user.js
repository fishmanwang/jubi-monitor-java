$(function () {

    init();
    bindBtnEvents();

});

var ctx = $("#ctx").val();
var limit = 10;

var usernameVal = "";
var nicknameVal = "";

function init() {
    var param = {
        page: 1,
        limit: limit
    };
    query(param);
}

function bindBtnEvents() {
    $("#userTable").off("click", ".del").on("click", ".del", function () {
        var userId = $(this).attr("item");
        var username = $(this).attr("uname");
        if (confirm("确认删除 " + username + "?")) {
            alert("删除 " + username + " 成功");
        }
    });

    $("#searchBtn").off("click").on("click", function () {
        usernameVal = $("#usernameInput").val().trim();
        nicknameVal = $("#nicknameInput").val().trim();
        fresh(1);
    });
}

function query(param) {
    $.getJSON(ctx + "/user/query", param, function (json) {
        console.log(json);
        if (json.status != 200) {
            alert(json.msg);
            return;
        }
        render(json.data);
        setPagination(json.data.page, json.data.totalPages, fresh);
    });
}

function fresh(page) {
    var param = {
        page: page,
        limit: limit,
        username: usernameVal,
        nickname: nicknameVal
    };
    query(param);
}

function render(data) {
    var c = '';
    var ds = data.items;
    ds.forEach(function (item) {
        c += '<tr class="line">'
            + '<td>' + item.username + '</td>'
            + '<td>' + item.nickname + '</td>'
            + '<td>' + item.phone + '</td>'
            + '<td>' + item.email + '</td>'
            + '<td>' + item.lastLoginIp + '</td>'
            + '<td>' + item.lastLoginTime + '</td>'
            + '<td><a class="del" item="' + item.id + '" uname="' + item.username + '">删除</a></td>'
            + '</tr>';
    });
    $("#userTable .line").remove();
    $("#userTable").append(c)
}

