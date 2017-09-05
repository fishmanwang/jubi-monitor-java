<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>价格提醒配置</title>
</head>
<body>

<style>
    /*.pricesSpan span {*/
        /*margin-right: 10px*/
    /*}*/
    .pricesSpan .priceSpanUnit {
        margin: 0 10px
    }

    .pricesSpan .pspan {
        margin-right: 5px
    }
</style>

<jsp:include page="header.jsp"/>

<div id="main">
    <div align="center">
        <b>使用方法：</b>
        <span style="color:orangered; font-size: 16px;">
            请先在 <a href="${ctx}/page/account/admin.html">账户管理</a> 中设置关注的虚拟币
        </span>，然后在对应的方块内输入指定价格，点击<b>Enter</b>，设置完成后记得点击<b>保存</b>。
    </div>
    <div id="configArea">
        <c:forEach items="${items}" var="item">
            <div style="clear:both; height:60px; margin-top:10px;">
                <div>${item.name} ${item.coin}:</div>
                <div style="margin:10px 0;padding-left: 20px;">
                    <div style="width: 160px; float: left;">当前价格: <span style="color:blue;">${item.price}</span></div>
                    <div style="width: 600px; float: left;">
                        <span><input class="nofityAddBtn" coin="${item.coin}" price="${item.price}"
                                     style="width: 80px;"/></span>
                        <span class="pricesSpan" coin="${item.coin}"></span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div style="margin-top: 10px">
        <input type="button" value="保存" id="saveBtn"/>
        <input type="button" value="取消" onclick="window.location.href='${ctx}/page/index.html'"/>
    </div>
    <p style="color:red; font-size: 12px;">注意：每个用户每天最多接收50封邮件，请合理设置监控的虚拟币和价格数量。</p>
</div>

<!-- ECharts单文件引入 -->
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/echarts/echarts.common.min.js"></script>
<script src="${ctx}/js/echarts/theme/macarons.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/price-notify-setting.js"></script>
</body>