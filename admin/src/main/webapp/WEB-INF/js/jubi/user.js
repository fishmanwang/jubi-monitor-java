$(function () {

    init();

});

var ctx = $("#ctx").val();
var limit = 10;

function init() {
    var param = {
        page: 1,
        limit: limit
    };
    query(param);
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
        limit: limit
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
            + '</tr>';
    });
    $("#userTable .line").remove();
    $("#userTable").append(c)
}

