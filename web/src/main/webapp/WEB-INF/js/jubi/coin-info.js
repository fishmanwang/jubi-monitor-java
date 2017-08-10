$(function() {
    if (!coin) {
        alert("请传入虚拟币");
        return;
    }
    queryCoinTicker();
    queryRealTimeDepth();
    queryLastOrderStatistic();
});

var coin = $("#coinInput").val();
var ctx = $("#ctx").val();

/**
 * 查询实时行情
 */
function queryCoinTicker() {
    var url = ctx + "/ticker/recent/" + coin + "?t=" + Math.random();
    $.getJSON(url, function (json) {
        if (json.status != '200') {
            alert(json.message);
            return
        }
        var ds = [].concat(json.data);
        var arr = prepareTickerData(ds);
        renderTicker(arr[0], arr[1])
    });
}

/**
 * 查询实时深度
 */
function queryRealTimeDepth() {
    var url = ctx + "/depth/real/" + coin + "?t=" + Math.random();
    $.getJSON(url, function (json) {
        if (json.status != '200') {
            alert(json.message);
            return;
        }
        console.log(json.data)
    });
}

function renderOrderInfo(json) {
    console.log(json.buyCount)
    $("#orderDiv").html("");
    var html = "<table width='400px'>"
        + "<tr><td width='25%'></td><td width='25%'>买</td><td width='25%'>卖</td><td>总计</td></tr>"
        + "<tr><td>交易次数</td><td>"+json.buyCount+"</td><td>"+json.sellCount+"</td><td>"+(json.buyCount+json.sellCount)+"</td></tr>"
        + "<tr><td>交易量</td><td>"+json.buyAmount+"</td><td>"+json.sellAmount+"</td><td>"+(json.buyAmount+json.sellAmount)+"</td></tr>"
        + "<tr><td>交易金额</td><td>"+json.buyTotal+"</td><td>"+json.sellTotal+"</td><td>"+(json.buyTotal+json.sellTotal)+"</td></tr>"
        "</table>";
    $("#orderDiv").html(html);
}

/**
 * 查询最近的订单交易信息
 */
function queryLastOrderStatistic() {
    var url = ctx + "/coin/order/oh/" + coin + "?t=" + Math.random();
    $.getJSON(url, function (json) {
        if (json.status != '200') {
            alert(json.message);
            return
        }
        renderOrderInfo(json.data);
    });
}

function prepareTickerData(ds) {
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
function renderTicker(xds, yds) {
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('tickerDiv'), 'macarons');

    var option = {
        title: {
            text: '行情走势'
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