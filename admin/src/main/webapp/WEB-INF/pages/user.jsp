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
    <title>用户管理</title>
</head>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/css/jquery.pagination.css"/>
<style>
    #userTable td {
        padding-top: 10px;
    }

</style>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="menu.jsp"/>
<div id="main" style="float:left;">
    <div id="content">
        <table id="userTable" width="800px">
            <tr class="head">
                <td>用户名</td>
                <td>昵称</td>
                <td>电话</td>
                <td>邮箱</td>
                <td>上次登录IP</td>
                <td>上次登录时间</td>
            </tr>
        </table>
    </div>
    <div class="box">
        <div id="pagination" class="page fl"></div>
    </div>
</div>

<div style="clear:both;"></div>

<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/jquery.pagination.min.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jubi/user.js"></script>
</body>
