<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="coin" value="${pageContext.request.getParameter('coin')}"/>

<tag:layout title="虚拟币信息">
    <input id="coinInput" type="hidden" value="${coin}"/>

    <div id="mainDiv">
        <div>
            <div><span>近期行情</span></div>
            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="tickerDiv" style="height:300px"></div>
        </div>

        <div>
            <div>
                <span>实时深度</span>&nbsp;
                <span><input id="depthShowBtn" type="button" value="查看/刷新"/></span>&nbsp;
                <span>买总金额: <span id="buyTotalSpan" style="margin-left:5px">0</span></span> &nbsp;
                <span>卖总金额:<span id="sellTotalSpan" style="margin-left:5px">0</span></span> &nbsp;
                <span>买/卖比率:<span id="rateSpan" style="margin-left:5px">0</span></span>
            </div>
            <div id="depthDiv" style="height:300px"></div>
        </div>

    </div>
</tag:layout>

<script type="text/javascript" src="${ctx}/js/jubi/coin-info.js"></script>