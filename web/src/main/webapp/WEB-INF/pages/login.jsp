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
    <title>登录</title>
</head>
<body>
    <input type="hidden" id="ctx" value="${ctx}"/>

    <form action="${ctx}/login" method="post">
        用户名：<input id="username" name="username"/><br/>
        密码：<input  id="password" type="password" name="password"/><br/>
        <input id="okBtn" type="button" value="登录"/>
    </form>

    <script src="${ctx}/js/jquery-1.12.1.min.js"></script>
    <script src="${ctx}/js/jubi/common.js"></script>
    <script src="${ctx}/js/jubi/login.js"></script>

</body>
