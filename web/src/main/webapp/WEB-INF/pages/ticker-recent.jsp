<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>最新行情</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div>
    <div align="center">
        <b>使用方法：</b> 选择虚拟币，展示当日行情。
    </div>
    <div style="margin-top:20px;">
        <select id="coinSel">
            <option value="">请选择币种</option>
            <c:forEach items="${coins}" var="coin">
                <option value="${coin.code}">${coin.name}</option>
            </c:forEach>
        </select>
    </div>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:600px"></div>
</div>
<!-- ECharts单文件引入 -->
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/echarts/echarts.common.min.js"></script>
<script src="${ctx}/js/echarts/theme/macarons.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/ticker-recent.js"></script>
</body>