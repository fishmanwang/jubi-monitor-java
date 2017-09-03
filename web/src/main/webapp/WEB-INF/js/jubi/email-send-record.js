$(function() {

    render()

});

var ctx = $("#ctx").val();

// function queryData() {
//     var url = ctx + "/email/record"
//     $.getJSON(url, function(json) {
//         if (json.status != 200) {
//             alert(json.message);
//             return
//         }
//         console.log(json.data)
//         var ds = buildData(json.data)
//         console.log(ds);
//     });
// }

function render() {
    var cols = ['提醒类型', '邮箱', '状态', '发送内容', '发送时间', '失败原因']

    var cs = [];
    $.each(cols, function (index, col) {
        cs.push({title: col})
    });

    var url = ctx + "/email/record?t=" + Math.random();
    var table = $('#mainTable').DataTable({
        "ajax": {
            "url": url,
            "dataSrc": buildData
        },
        columns: cs,
        "order": [[ 4, 'desc' ]],
        autoWidth: false,
        info: true,
        paging: false,
        scrollY: 600
    });
}

function buildData(json) {
    var ds = json.data;
    console.log(ds)
    if (!ds || ds.length == 0) {
        return [];
    }
    var datas = [];
    ds.forEach(function(d) {
        var data = [];
        data.push(transNotifyType(d.notifyType));
        data.push(d.email);
        data.push(d.succ ? '发送成功': '发送失败');
        data.push(d.content);
        data.push(d.sendTime);
        data.push(d.reason);
        datas.push(data);
    });
    console.log(datas)
    return datas
}

function transNotifyType(type) {
    var r = "未知";
    if (type == 1) {
        r = '价格提醒'
    } else if (type == 2) {
        r = '波动提醒'
    } else if(type == 3) {
        r = '涨幅提醒'
    }
    return r
}