<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>涨幅</title>
</head>
<body>

<select id="coinSel">
    <option value="">请选择币种</option>
    <c:forEach items="${coins}" var="coin">
        <option value="${coin.code}">${coin.name}</option>
    </c:forEach>
</select>

<select id="spanSel">
    <option value="60" selected="selected">一分钟</option>
    <option value="${60 * 5}">五分钟</option>
    <option value="${60 * 10}">十分钟</option>
    <option value="${60 * 30}">三十分钟</option>
    <option value="${60 * 60}">一小时</option>
    <option value="${60 * 60 * 6}">六小时</option>
    <option value="${60 * 60 * 12}">十二小时</option>
    <option value="${60 * 60 * 24}">一日</option>
</select>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:600px"></div>

<!-- ECharts单文件引入 -->
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/echarts/echarts.common.min.js"></script>
<script src="${ctx}/js/echarts/theme/macarons.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/ticker-rate.js"></script>
</body>