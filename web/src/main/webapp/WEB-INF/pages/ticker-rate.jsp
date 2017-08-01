<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>涨幅</title>
</head>
<body>

<%--<select id="coinSel">--%>
<%--<option value="">请选择币种</option>--%>
<%--<c:forEach items="${coins}" var="coin">--%>
<%--<option value="${coin.code}">${coin.name}</option>--%>
<%--</c:forEach>--%>
<%--</select>--%>

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
        <select id="spanSel">
            <option value="60">一分钟</option>
            <option value="${5*60}" selected="selected">五分钟</option>
            <option value="${10*60}">十分钟</option>
            <option value="${30*60}">三十分钟</option>
        </select>
    </div>
    <div style="float: left;">
        <button onclick="fetchAndRender()">确定</button>
    </div>
</div>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:600px"></div>
<!-- ECharts单文件引入 -->
<script src="../js/jquery-1.12.1.min.js"></script>
<script src="../js/echarts/echarts.common.min.js"></script>
<script src="../js/echarts/theme/macarons.js"></script>

<script src="../js/jubi/common.js"></script>

<script type="text/javascript" src="../js/jubi/ticker-rate.js"></script>
</body>