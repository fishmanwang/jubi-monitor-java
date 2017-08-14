<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/27 0027
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <title>首页</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div style="float: left;">
    <div>
        <h3>行情</h3>
        <a href="${ctx}/page/ticker.html">行情总览</a>
        <a href="${ctx}/page/recent/ticker.html">最新行情</a>
        <a href="${ctx}/page/history/ticker.html">历史行情</a>
    </div>
    <div>
        <h3>涨幅</h3>
        <a href="${ctx}/page/rate.html">涨幅总览</a>
        <a href="${ctx}/page/recent/rate.html">最新涨幅</a>
        <a href="${ctx}/page/history/rate.html">历史涨幅</a>
    </div>
    <%--<div>--%>
    <%--<h3>深度</h3>--%>
    <%--<a href="${ctx}/page/coin/depth/cmp.html">深度对比</a>--%>
    <%--<a href="${ctx}/page/coin/depth/history.html">深度历史</a>--%>
    <%--</div>--%>
    <div>
        <h3>概览</h3>
        <select id="coinSel">
            <option value="">请选择币种</option>
            <c:forEach items="${coins}" var="coin">
                <option value="${coin.code}">${coin.name}</option>
            </c:forEach>
        </select>

    </div>
</div>

<div style="float: left; margin-left: 30px;">
    <h3>涨幅排行</h3>
    <table width="300">
        <tr>
            <td width="20%">排行</td>
            <td width="40%">名称</td>
            <td>涨幅</td>
        </tr>
        <c:forEach var="item" varStatus="status" items="${rankedRate}">
            <tr>
                <td>${status.index + 1}</td>
                <td>${item.name}</td>
                <td>${item.rate}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jubi/index.js"></script>
</body>
