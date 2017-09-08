<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<tag:layout title="最新行情">
    <div>
        <div align="center">
            <b>使用方法：</b> 选择虚拟币，展示当日行情。
        </div>
        <div style="margin-top:20px;">
            <select id="tcCoinSel">
                <option value="">请选择币种</option>
                <c:forEach items="${coins}" var="coin">
                    <option value="${coin.code}">${coin.name}</option>
                </c:forEach>
            </select>
        </div>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="height:600px"></div>
    </div>
</tag:layout>

<script type="text/javascript" src="${ctx}/js/jubi/ticker-recent.js"></script>
