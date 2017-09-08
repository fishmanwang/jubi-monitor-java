<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<tag:layout title="历史行情">
    <select id="thCoinSel">
        <option value="">请选择币种</option>
        <c:forEach items="${coins}" var="coin">
            <option value="${coin.code}">${coin.name}</option>
        </c:forEach>
        <input id="dateInput"/>&nbsp;<input id="prevBtn" type="button" value="上一天"/>&nbsp;
        <input id="nextBtn" type="button" value="下一天"/>
    </select>
</tag:layout>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>历史行情</title>
</head>
<body>

<jsp:include page="header.jsp"/>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:600px"></div>
<!-- ECharts单文件引入 -->
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/echarts/echarts.common.min.js"></script>
<script src="${ctx}/js/echarts/theme/macarons.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/ticker-history.js"></script>
</body>