$(function () {

    $("#tickerCoinSel").off("change").on("change", function () {
        fetchAndRender()
    });

    $("#spanSel").off("change").on("change", function () {
        fetchAndRender()
    })
});

function fetchAndRender() {

    var coin = $("#tickerCoinSel").val();
    if (!coin) {
        return
    }
    var span = $("#spanSel").val();
    if (!span) {
        return
    }

    var ctx = $("#ctx").val();
    var url = ctx + "/ticker/" + coin + "?span=" + span + "&t=" + Math.random();
    $.getJSON(url, function (json) {
        if (json.status != '200') {
            alert(json.message);
            return
        }
        var ds = [].concat(json.data);
        ds.reverse();
        var arr = prepareData(ds);
        render(arr[0], arr[1])
    });
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
function render(xds, yds) {
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
                        {name: '最小值', type: 'min'}
                    ]
                }
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
}