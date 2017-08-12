<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="coin" value="${pageContext.request.getParameter('coin')}"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>虚拟币信息</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<input id="coinInput" type="hidden" value="${coin}"/>

<div id="mainDiv">
    <div>
        <div><span>近期行情</span>&nbsp;<span><input id="tickerShowBtn" type="button" value="查看/刷新"/></span></div>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="tickerDiv" style="height:300px"></div>
    </div>

    <div>
        <div>
            <span>实时深度</span>&nbsp;
            <span><input id="depthShowBtn" type="button" value="查看/刷新"/></span>&nbsp;
            <span>买总金额: <span id="buyTotalSpan" style="margin-left:5px">0</span></span> &nbsp;
            <span>卖总金额:<span id="sellTotalSpan" style="margin-left:5px">0</span></span> &nbsp;
            <span>买/卖比率:<span id="rateSpan" style="margin-left:5px">0</span></span>
        </div>
        <div id="depthDiv" style="height:300px"></div>
    </div>

    <div>
        <div><span>近期交易情况</span>&nbsp;<span><input id="orderShowBtn" type="button" value="查看/刷新"/></span></div>
        <div id="orderDiv">

        </div>
    </div>

</div>

<!-- ECharts单文件引入 -->
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/echarts/echarts.common.min.js"></script>
<script src="${ctx}/js/echarts/theme/macarons.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/coin-info.js"></script>
</body>