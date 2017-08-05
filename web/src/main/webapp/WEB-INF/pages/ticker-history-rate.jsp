<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>涨幅</title>
</head>
<body>

<jsp:include page="header.jsp" />

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:600px"></div>

<div>
    <div style="float: left;">
        <ul>
            <c:forEach items="${coins}" var="coin">
                <li>
                    <input class="coinChk" type="checkbox" name="coinChk" value="${coin.code}"/>${coin.name}
                </li>
            </c:forEach>
        </ul>
    </div>
    <div style="float: left;">
        <input id="dateInput"/>
    </div>
    <div style="float: left;">
        <button id="okBtn">确定</button>
    </div>
</div>

<!-- ECharts单文件引入 -->
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/echarts/echarts.common.min.js"></script>
<script src="${ctx}/js/echarts/theme/macarons.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/ticker-history-rate.js"></script>
</body>