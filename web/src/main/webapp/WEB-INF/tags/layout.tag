<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/27 0027
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <title>${title}</title>
</head>
<link href="${ctx}/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css">
<body>
<div id="main" class="main"></div>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<jsp:include page="/WEB-INF/pages/menu.jsp"/>

<div id="mbody" style="float:left;">
    <jsp:doBody></jsp:doBody>
</div>

<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/jquery.dataTables.min.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>
</body>
