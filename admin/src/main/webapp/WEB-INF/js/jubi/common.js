/**
 * 设置分页
 * @param page
 * @param totalPages
 * @param callback
 */
function setPagination(page, totalPages, callback) {
    if ($("#pagination").html().trim() == '') {
        $("#pagination").pagination({
            currentPage: page,
            totalPage: totalPages,
            isShow: true,
            count: 5,
            homePageText: "首页",
            endPageText: "尾页",
            prevPageText: "上一页",
            nextPageText: "下一页",
            callback: function (current) {
                callback(current)
            }
        });
    } else {
        $("#pagination").pagination("setPage", page, totalPages);
    }
}