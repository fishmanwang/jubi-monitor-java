<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div style="float: left; width:13%; height: 600px;">
    <div>
        <h3>首页</h3>
        <a href="${ctx}/page/index.html">首页</a>
    </div>
    <div>
        <h3>行情</h3>
        <a href="${ctx}/page/ticker.html">行情总览</a><br/><br/>
        <a href="${ctx}/page/recent/ticker.html">当日行情</a>
        <!--
        <a href="${ctx}/page/history/ticker.html">历史行情</a>
        -->
    </div>
    <div>
        <h3>涨幅</h3>
        <a href="${ctx}/page/rate.html">涨幅总览</a>
        <!--
        <a href="${ctx}/page/recent/rate.html">最新涨幅</a>
        <a href="${ctx}/page/history/rate.html">历史涨幅</a>
        -->
    </div>
    <div>
        <h3>概览</h3>
        <select id="coinSel">
            <option value="">请选择币种</option>
            <c:forEach items="${coins}" var="coin">
                <option value="${coin.code}">${coin.name}</option>
            </c:forEach>
        </select>

    </div>
    <div>
        <h3>账户信息</h3>
        <a href="${ctx}/page/account/admin.html">账户管理</a>
    </div>
    <div>
        <h3>监控提醒</h3>
        <a href="${ctx}/page/notify/price.html">价格提醒</a><br/><br/>
        <a href="${ctx}/page/notify/wave.html">波动提醒</a><br/><br/>
        <a href="${ctx}/page/notify/rate.html">涨幅提醒</a>
    </div>
    <div>
        <h3>消息记录</h3>
        <a href="${ctx}/page/email/record.html">邮件记录</a>
    </div>
</div>