<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>实时深度</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div style="text-align:center">
    <h3>虚拟币交易</h3>
</div>
<div style="margin:0 0 20px 0">
    <select id="coinSel">
        <option value="">请选择币种 ${coin}</option>
        <c:forEach items="${coins}" var="item">
            <c:choose>
                <c:when test="${coin == item.code}">
                    <option value="${item.code}" selected="selected">${item.name}</option>
                </c:when>
                <c:otherwise>
                    <option value="${item.code}">${item.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</div>
<div id="mainDiv">

</div>


<!-- ECharts单文件引入 -->
<link href="${ctx}/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/coin-order.js"></script>
</body>