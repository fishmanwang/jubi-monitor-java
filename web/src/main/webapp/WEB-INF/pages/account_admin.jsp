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
<body>

<jsp:include page="header.jsp"/>

<div align="center">
    <div>
        <h3>基础信息</h3>
        <table>
            <tr>
                <td>昵称：</td>
                <td><input id="nicknameInput" value="${account.nickname}"/><br></td>
            </tr>
            <tr>
                <td>电话：</td>
                <td><input id="phoneInput" value="${account.phone}"/><br></td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td><input id="emailInput" value="${account.email}"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input id="baseCommitBtn" type="button" value="提交"/></td>
            </tr>
        </table>
    </div>

    <div>
        <h3>关注虚拟币</h3>
        <div>
            <c:forEach items="${coins}" var="coin">
                <span width="60px"><input class="coinChk" code="${coin.code}" type="checkbox"
                                          value="${coin.code}"/>${coin.name}</span>
            </c:forEach>
        </div>
        <div>
            <input id="saveFcoinsBtn" type="button" value="保存"/>
            <input type="button" value="取消" onclick="window.location.href='${ctx}/page/index.html'"/>
        </div>
    </div>

</div>


<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jubi/account_admin.js"></script>
</body>
