$(function () {
    $("#coinSel").off("change").on("change", function () {
        fetchAndRender()
    });

    $('#dateInput').keydown(function(e){
        if(e.keyCode==13){
            var coin = $("#coinSel").val()
            if (!coin) {
                alert("请选择币种");
                return;
            }
            fetchAndRender();
        }
    });

    var s = formatDate(new Date());
    $("#dateInput").val(s);
});

function fetchAndRender() {
    var coin = $("#coinSel").val()
    if (!coin) {
        return
    }

    var time = $('#dateInput').val()

    var url = "/ticker/history/" + coin + "?time=" + time + "&t=" + Math.random();
    $.getJSON(url, function (json) {
        if (json.status != '200') {
            alert(json.message)
            return
        }
        var ds = [].concat(json.data)
        var arr = prepareData(ds)
        var point = getOriginPoint(ds)
        render(arr[0], arr[1], point)
    });
}

function getOriginPoint(ds) {
    var date = new Date();
    var t = date.getTime()
    var offset = date.getTimezoneOffset() * 60 * 1000
    t = (t - t % 86400000) + offset
    t = t / 1000;
    var r = []
    for (var i = 0; i < ds.length; i++) {
        if (ds[i].pk <= t) {
            r = [formatDateTimeSecsForX(t), ds[i].price]
            break
        }
    }
    return r
}

function prepareData(ds) {
    var len = ds.length
    var xds = [len];
    var yds = [len];
    for (var i = 0; i < len; i++) {
        xds[i] = formatDateTimeSecsForX(ds[i].pk)
        yds[i] = ds[i].price
    }
    return [xds, yds]
}

/**
 *
 * @param xds
 * @param yds
 * @param origin 最近一天开盘价
 */
function render(xds, yds, origin) {
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'), 'macarons');

    var option = {
        title: {
            text: '行情走势'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['阿希币', '点点币']
        },
        toolbox: {
            show: false,
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: xds
            }
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                axisLabel: {
                    formatter: '{value} 元'
                }
            }
        ],
        series: [
            {
                name: '价格',
                type: 'line',
                data: yds,
                markPoint: {
                    symbolSize: 100,
                    data: [
                        {name: '最大值', type: 'max'},
                        {name: '最小值', type: 'min'},
                        {name: '开盘价', coord: origin}
                    ]
                }
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
}