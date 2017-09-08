<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div id="header" style="height:50px; margin-right: 70px;">
    <div style="float:right">
        <span>
            <a href="${ctx}/page/account/admin.html"><shiro:principal property="nickname"/></a>
            <span style="color: orangered;">L<shiro:principal property="grade"/></span>
        </span>

        <a href="${ctx}/logout">退出</a>
    </div>
</div>
