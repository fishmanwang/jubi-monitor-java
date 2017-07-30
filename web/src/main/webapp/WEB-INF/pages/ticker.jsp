<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:400px"></div>
<!-- ECharts单文件引入 -->
<script src="../js/jquery-1.12.1.min.js"></script>
<script src="../js/echarts/echarts.common.min.js"></script>
<script src="../js/echarts/theme/macarons.js"></script>

<script src="../js/jubi/common.js"></script>

<script type="text/javascript">
    $(function () {
        url = "/ticker/xas/recently?span=60";
        $.getJSON(url, function(json) {
            if (json.status != '200') {
                alert(json.message)
                return
            }

            var arr = prepareData(json.data.reverse())
            render(arr[0], arr[1])
        });
    });

    function prepareData(ds) {
        var len = ds.length
        var xds = [len];
        var yds = [len];
        for (var i=0; i<len ; i++) {
            xds[i] = formatDateTimeSecs(ds[i].pk)
            yds[i] = ds[i].price
        }
        return [xds, yds]
    }

    function render(xds, yds) {
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main'), 'macarons');

        option = {
            title : {
                text: '未来一周气温变化',
                subtext: '纯属虚构'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['阿希币','点点币']
            },
            toolbox: {
                show : false,
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : xds
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    scale : true,
                    axisLabel : {
                        formatter: '{value} 元'
                    }
                }
            ],
            series : [
                {
                    name:'价格',
                    type:'line',
                    data: yds,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    }
                }
            ]
        };

        // 为echarts对象加载数据
        myChart.setOption(option);
    }





</script>
</body>