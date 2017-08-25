<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<input type="hidden" id="ctx" value="${ctx}"/>
<a href="${ctx}/page/index.html">首页</a>&nbsp;<a href="${ctx}/logout">退出</a>