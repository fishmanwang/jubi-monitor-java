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

<div>
    实时深度
</div>

<div id="content">

</div>

<div>
    <table id="main" class="display" width="100%"></table>
</div>


<!-- ECharts单文件引入 -->
<link href="${ctx}/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/depth.js"></script>
</body>