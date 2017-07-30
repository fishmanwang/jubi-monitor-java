
// 格式化日期 年-月-日
var formatDate = function(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '-' + m + '-' + d;
}

// 格式化日期 月/日 时:分
var formatDateTimeSecsForX = function(secs) {
    return formatDateTime2(new Date(secs * 1000))
}

// 格式化日期 年-月-日 时:分:秒
var formatDateTime = function(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h=h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    var second=date.getSeconds();
    second=second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
}

// 格式化日期 月/日 时:分
var formatDateTime2 = function(date) {
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h=h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    return m + '/' + d+' '+h+':'+minute;
}