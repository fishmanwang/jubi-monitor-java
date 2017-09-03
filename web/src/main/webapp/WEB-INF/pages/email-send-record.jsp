<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>邮件发送记录</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div id="main">
    <div align="center">
        <b>功能介绍：</b>查看最近发送的50消息，可用于查看消息发送状态。
    </div>
    <div id="mainArea" style="width:80%; margin-top: 20px; padding-left: 200px;" align="center">
        <table id="mainTable" class="display" width="100%"></table>
    </div>
</div>

<!-- ECharts单文件引入 -->
<link href="${ctx}/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/jquery.dataTables.min.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/email-send-record.js"></script>
</body>