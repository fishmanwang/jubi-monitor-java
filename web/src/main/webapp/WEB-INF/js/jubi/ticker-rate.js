$(function () {
    $("#coinSel").off("change").on("change", function () {
        fetchAndRender()
    });

    $("#spanSel").off("change").on("change", function () {
        fetchAndRender()
    })
});

function fetchAndRender() {
    var coin = $("#coinSel").val();
    if (!coin) {
        return
    }
    var span = $("#spanSel").val();
    if (!span) {
        return
    }
    var ctx = $("#ctx").val();
    var url = ctx + "/rate/" + coin + "?span=" + span + "&t=" + Math.random();
    $.getJSON(url, function (json) {
        if (json.status != '200') {
            alert(json.message);
            return
        }
        console.log(json.data)
        var ds = [].concat(json.data);
        ds.reverse();
        var arr = prepareData(ds);
        render(arr[0], arr[1])
    });
}

function prepareData(ds) {
    var len = ds.length
    var xds = []
    var yds = []
    for (var i = 0; i < len; i++) {
        var pk = ds[i].pk
        if (xds.length == 0 || pk > xds[xds.length - 1])
            xds.push(pk)
        yds.push(ds[i].rate)
    }
    var xdsStr = []
    for (var i = 0; i < xds.length; i++) {
        xdsStr.push(formatDateTimeSecsForX(xds[i]))
    }
    console.log([xdsStr, yds])
    return [xdsStr, yds]
}

/**
 *
 * @param xds
 * @param yds
 */
function render(xds, yds) {
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'), 'macarons');

    var option = {
        title: {
            text: '涨幅情况'
        },
        tooltip: {
            trigger: 'axis'
        },
        toolbox: {
            show: false,
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: xds
            }
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        series: [
            {
                name: '涨幅',
                type: 'line',
                data: yds,
                markPoint: {
                    symbolSize: 100,
                    data: [
                        {name: '最大值', type: 'max'},
                        {name: '最小值', type: 'min'}
                    ]
                }
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
}