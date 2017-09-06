$(function () {


    init();

});

var ctx = $("#ctx").val();
var limit = 3;

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
        setPagination(json.data);
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
        c += '<tr>'
            + '<td>' + item.username + '</td>'
            + '<td>' + item.nickname + '</td>'
            + '<td>' + item.phone + '</td>'
            + '<td>' + item.email + '</td>'
            + '<td>' + item.lastLoginIp + '</td>'
            + '<td>' + item.lastLoginTime + '</td>'
            + '</tr>';
    });
    $("#userTable").append(c)
}

function setPagination(data) {
    if ($("#pagination").html().trim() == '') {
        $("#pagination").pagination({
            currentPage: data.page,
            totalPage: data.totalPages,
            isShow: true,
            count: 5,
            homePageText: "首页",
            endPageText: "尾页",
            prevPageText: "上一页",
            nextPageText: "下一页",
            callback: function (current) {
                fresh(current)
            }
        });
    } else {
        $("#pagination").pagination("setPage", data.page, data.totalPages);
    }

}

