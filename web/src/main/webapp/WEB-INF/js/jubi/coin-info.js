$(function () {
    if (!coin) {
        alert("请传入虚拟币");
        return;
    }
    queryCoinTicker()
    bindBtns();
});

var coin = $("#coinInput").val();
var ctx = $("#ctx").val();

function bindBtns() {
    $("#depthShowBtn").off("click").on("click", function () {
        queryRealTimeDepth();
    });
}

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
        var asks = json.data.asks.reverse();
        var bids = json.data.bids.reverse();
        var price = json.data.price;
        var xds = [];
        var yds = [];
        var buyTotal = 0;
        var sellTotal = 0;
        $.each(bids, function (index, data) {
            xds.push(data[0]);
            yds.push(data[1]);
            buyTotal += data[0] * data[1];
        });
        xds.push(price);
        yds.push(0);
        $.each(asks, function (index, data) {
            xds.push(data[0]);
            yds.push(data[1]);
            sellTotal += data[0] * data[1];
        });
        $("#buyTotalSpan").text(Math.round(buyTotal));
        $("#sellTotalSpan").text(Math.round(sellTotal));
        $("#rateSpan").text((buyTotal / sellTotal).toFixed(2));
        renderDepth(xds, yds);
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

var depthCharts = echarts.init(document.getElementById('depthDiv'), 'macarons');
/**
 * 渲染实时深度
 * @param price
 * @param xds
 * @param yds
 */
function renderDepth(xds, yds) {
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: xds
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '数量',
                type: 'bar',
                data: yds,
                markPoint: {
                    symbolSize: 100,
                    data: [
                        {type: "min", name: "当前价格"}
                    ]
                }
            }
        ]
    };

    depthCharts.setOption(option);
}

// 基于准备好的dom，初始化echarts图表
var tickerCharts = echarts.init(document.getElementById('tickerDiv'), 'macarons');
/**
 *
 * @param xds
 * @param yds
 * @param origin 最近一天开盘价
 */
function renderTicker(xds, yds) {
    var option = {
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
    tickerCharts.setOption(option);
}