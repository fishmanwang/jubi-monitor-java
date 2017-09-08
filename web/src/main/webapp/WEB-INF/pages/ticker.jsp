<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<tag:layout title="行情展示">
    <div>
        <div align="center">
            <b>使用方法：</b> 选择虚拟币，该页面最多展示2000条数据，间隔跨度越大，跨越的天数越多。
        </div>
        <div style="margin-top: 20px;">
            <select id="tickerCoinSel">
                <option value="">请选择币种</option>
                <c:forEach items="${coins}" var="coin">
                    <option value="${coin.code}">${coin.name}</option>
                </c:forEach>
            </select>

            <select id="spanSel">
                <option value="60" selected="selected">一分钟</option>
                <option value="${60 * 5}">五分钟</option>
                <option value="${60 * 10}">十分钟</option>
                <option value="${60 * 30}">三十分钟</option>
                <option value="${60 * 60}">一小时</option>
                <option value="${60 * 60 * 6}">六小时</option>
                <option value="${60 * 60 * 12}">十二小时</option>
                <option value="${60 * 60 * 24}">一日</option>
            </select>
        </div>

        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="height:600px"></div>
    </div>
</tag:layout>

<script type="text/javascript" src="${ctx}/js/jubi/ticker.js"></script>