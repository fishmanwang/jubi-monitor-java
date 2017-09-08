<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<tag:layout title="邮件发送记录">
    <div id="main">
        <div align="center">
            <b>功能介绍：</b>查看最近发送的50消息，可用于查看消息发送状态。
        </div>
        <div id="mainArea" style="margin-top: 20px;" align="center">
            <table id="mainTable" class="display" width="100%"></table>
        </div>
    </div>
</tag:layout>

<script type="text/javascript" src="${ctx}/js/jubi/email-send-record.js"></script>