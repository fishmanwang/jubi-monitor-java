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
<html>
<head>
    <title>首页</title>
</head>
<body>

    <jsp:include page="header.jsp" />

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
    </div>

    <div style="float: left; margin-left: 30px;">
        <h3>涨幅排行</h3>
        <table width="300">
            <tr>
                <td width="40%">名称</td>
                <td>涨幅</td>
            </tr>
            <c:forEach var="item" items="${rankedRate}">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.rate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
