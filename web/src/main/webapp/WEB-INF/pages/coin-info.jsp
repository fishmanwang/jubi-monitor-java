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

<jsp:include page="header.jsp" />

<input id="coinInput" type="hidden" value="${coin}"/>

<div id="mainDiv">
    <div>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="tickerDiv" style="height:600px"></div>
    </div>

    <div>
        <div>实时深度</div>
        <div id="depthDiv"></div>
    </div>

    <div>
        <div>近期交易情况</div>
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