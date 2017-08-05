$(function () {
    $("#okBtn").off("click").on("click", function () {
        fetchAndRender();
    });

    $("#prevBtn").off("click").on("click", function() {
        var time = $('#dateInput').val();
        var d = new Date(time);
        d = new Date(d.getTime() - dayLong);
        $('#dateInput').val(formatDateTime3(d));
        fetchAndRender();
    });

    $("#nextBtn").off("click").on("click", function() {
        var time = $('#dateInput').val();
        var d = new Date(time);
        d = new Date(d.getTime() + dayLong);

        if (largetThanToday(d)) {
            alert("不能大于当前日期");
            return;
        }

        $('#dateInput').val(formatDateTime3(d));
        fetchAndRender();
    });

    var t = new Date().getTime() - 24*60*60* 1000;
    var s = formatDate(new Date(t));
    $("#dateInput").val(s);
});

function fetchAndRender() {
    var time = $("#dateInput").val();

    var ct = currentDayBeginTime();
    var tt = new Date(time).getTime();
    if (ct <= tt) {
        alert("不能大于当前日期");
        return;
    }

    var cs = $(".coinChk:checked")
    if (cs.length == 0) {
        alert("请选择币种");
        return
    }

    var coins = []
    for (var i = 0; i < cs.length; i++) {
        coins.push($(cs[i]).val())
    }

    var coinsParam = ""
    for (var i = 0; i < coins.length; i++) {
        coinsParam += "&coins=" + coins[i]
    }

    var ctx = $("#ctx").val();
    var url = ctx + "/rate/history?t=" + Math.random() + coinsParam + "&time=" + time;

    $.getJSON(url, function (json) {
        if (json.status != '200') {
            alert(json.message)
            return
        }
        var arr = prepareData(json.data.reverse())
        render(arr)
    });
}

function prepareData(ds) {
    var r = {}
    var len = ds.length
    var xds = []
    for (var i = 0; i < len; i++) {
        var coin = ds[i].coin
        var d = r[coin]
        if (!d) {
            d = {
                xds: [],
                yds: []
            }
            r[coin] = d
        }
        var pk = ds[i].pk
        if (xds.length == 0 || pk > xds[xds.length - 1])
            xds.push(pk)
        d.yds.push(ds[i].rate)
    }
    var xdsStr = []
    for (var i = 0; i < xds.length; i++) {
        xdsStr.push(formatDateTimeSecsForX(xds[i]))
    }
    return [xdsStr, r]
}

/**
 *
 * @param xds
 * @param yds
 */
function render(data) {
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'), 'macarons');

    var xds = data[0]
    var seriesJson = data[1]
    var seriesArr = []
    var coins = []

    for (var coin in seriesJson) {
        var item = {
            name: coin,
            type: 'line',
            data: seriesJson[coin].yds
        }
        coins.push(coin)
        seriesArr.push(item)
    }

    var option = {
        title: {
            text: '涨幅情况'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: coins
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
        series: seriesArr
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
}

/**
 * 参数日期只能比当天时间小
 * @param d
 * @returns {boolean}
 */
function largetThanToday(d) {
    var ct = currentDayBeginTime();
    var tt = d.getTime();
    if (ct <= tt) {
        return true;
    }
    return false
}