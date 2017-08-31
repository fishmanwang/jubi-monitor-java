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
<style>
    body {
        text-align: center
    }

    .center {
        margin: 200px auto;
        width: 400px;
    }

    /* css注释：为了观察效果设置宽度 边框 高度等样式 */
</style>
<body>
<input type="hidden" id="ctx" value="${ctx}"/>

<div class="center">
    <h3>虚拟币量化监控系统</h3>
    <div align="center">
        <form action="${ctx}/login" method="post">
            <table>
                <tr>
                    <td>用户名：</td>
                    <td><input id="username" name="username"/><br/></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input id="password" type="password" name="password"/><br/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input id="okBtn" type="button" value="登录"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/jubi/common.js"></script>
<script src="${ctx}/js/jubi/login.js"></script>

</body>
