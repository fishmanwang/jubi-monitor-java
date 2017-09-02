<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>价格波动提醒配置</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div id="main">
    <div align="center">
        <b>使用方法：</b>
        <span style="color:orangered; font-size: 16px;">
            请先在 <a href="${ctx}/page/account/admin.html">账户管理</a> 中设置关注的虚拟币
        </span>，然后选择虚拟币对应的监控幅度，点击
        <b>保存</b>。
    </div>
    <div id="configArea" style="width:800px; margin-top: 20px">
        <div>
            <c:forEach items="${items}" var="item">
                <div class="setting" coin="${item.coin}" style="margin: 15px 0;">
                    <div style="width:120px; display: inline-block;">
                        <span>${item.name} <span style="color:#999;">${item.coin}</span></span>
                    </div>
                    <div style="width:120px; display: inline-block;">
                        <span>
                            <select class="rateSelect">
                                <option value="0">请选择监控幅度</option>
                                <c:forEach items="${validRates}" var="vr">
                                    <c:choose>
                                        <c:when test="${vr == item.rate}">
                                            <option value="${vr}" selected="selected">${vr}%</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${vr}">${vr}%</option>
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>
                            </select>
                        </span>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div style="margin-top: 10px">
            <input type="button" value="保存" id="saveBtn"/>
            <input type="button" value="取消" onclick="window.location.href='${ctx}/page/index.html'"/>
        </div>
        <div>
            <p style="color:red; font-size: 12px;">注意：每个用户每天最多接收50封邮件，请合理设置监控的虚拟币和幅度。</p>
        </div>
    </div>
</div>

<!-- ECharts单文件引入 -->
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/echarts/echarts.common.min.js"></script>
<script src="${ctx}/js/echarts/theme/macarons.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/price-rate-notify-setting.js"></script>
</body>