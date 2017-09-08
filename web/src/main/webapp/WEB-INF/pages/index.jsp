<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/27 0027
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<tag:layout title="首页">
    <div style="float: left;" id="tickerMainDiv" width="60%">
        <table id="tickerMainTable" class="display" width="100%"></table>
    </div>
</tag:layout>


<script type="text/javascript" src="${ctx}/js/jubi/index.js"></script>
