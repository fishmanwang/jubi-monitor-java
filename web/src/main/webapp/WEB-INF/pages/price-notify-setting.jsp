<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>价格提醒配置</title>
</head>
<body>

<style>
    span {
        margin-right: 20px
    }
</style>

<jsp:include page="header.jsp"/>

<div id="main">
    <div id="configArea">
        <c:forEach items="${items}" var="item">
            <div>
                <span>${item.name}:</span>
                <span>${item.price}</span>
                <span><input class="nofityAddBtn" coin="${item.coin}" price="${item.price}"
                             style="width: 80px;"/></span>
                <span class="pricesSpan" coin="${item.coin}"></span>
            </div>
        </c:forEach>
    </div>
    <div style="margin-top: 10px">
        <input type="button" value="保存" id="saveBtn"/>
        <input type="button" value="取消" onclick="window.location.href='${ctx}/page/index.html'"/>
    </div>
</div>

<!-- ECharts单文件引入 -->
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/echarts/echarts.common.min.js"></script>
<script src="${ctx}/js/echarts/theme/macarons.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/price-notify-setting.js"></script>
</body>