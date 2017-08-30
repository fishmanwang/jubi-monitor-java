<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div id="header" style="height:30px;">
    <input type="hidden" id="ctx" value="${ctx}"/>
    <div style="float:left;">
        <a href="${ctx}/page/index.html">首页</a>
    </div>
    <div style="float:right">
        <a href="${ctx}/logout">退出</a>
    </div>
</div>
