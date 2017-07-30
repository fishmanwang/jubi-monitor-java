<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>
<body>

<select id="coinSel">
    <option value="">请选择币种</option>
    <c:forEach items="${coins}" var="coin">
        <option value="${coin.code}">${coin.desc}</option>
    </c:forEach>
</select>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:600px"></div>
<!-- ECharts单文件引入 -->
<script src="../js/jquery-1.12.1.min.js"></script>
<script src="../js/echarts/echarts.common.min.js"></script>
<script src="../js/echarts/theme/macarons.js"></script>

<script src="../js/jubi/common.js"></script>

<script type="text/javascript">

    $(function () {
        $("#coinSel").off("change").on("change", function() {
            coin = $(this).val()
            if (coin != '') {
                url = "/ticker/recent/" + coin + "?span=60&t=" + Math.random();
                $.getJSON(url, function(json) {
                    if (json.status != '200') {
                        alert(json.message)
                        return
                    }
                    var arr = prepareData(json.data.reverse())
                    render(arr[0], arr[1])
                });
            }
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
                text: '行情走势'
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
                        symbolSize : 100,
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