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
        <b>使用方法：</b> 在对应的方块内输入指定价格，点击<b>Enter</b>，设置完成后记得点击<b>保存</b>。
    </div>
    <div id="configArea">
        <div>
            <select id="coinSel">
                <option>请选择虚拟币</option>
                <c:forEach items="${items}" var="item">
                    <option value="${item.coin}">${item.name}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <c:forEach items="${items}" var="item">
                <div class="settingDiv" coin="${item.coin}" style="display: none;" setted="${item.setted}">
                    <div style="float: left;display: inline-block; width: 120px;">
                        <span>${item.name} ${item.coin}</span>
                    </div>
                    <div style="float: left">
                        间隔&nbsp;<input class="spanInput" value="${item.span}" style="width:60px"/>&nbsp;
                        分钟内波动&nbsp;<input class="rateInput" value="${item.rate}" style="width:60px"/>&nbsp;%&nbsp;提醒
                    </div>
                    <div>
                        <input type="button" class="delSetting" coin="${item.coin}" value="删除"/>
                    </div>
                    <div style="clear:both"></div>
                </div>
            </c:forEach>
        </div>

    </div>
</div>
<div style="margin-top: 10px">
    <input type="button" value="保存" id="saveBtn"/>
    <input type="button" value="取消" onclick="window.location.href='${ctx}/page/index.html'"/>
</div>
</div>

<!-- ECharts单文件引入 -->
<script src="${ctx}/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/js/echarts/echarts.common.min.js"></script>
<script src="${ctx}/js/echarts/theme/macarons.js"></script>

<script src="${ctx}/js/jubi/common.js"></script>

<script type="text/javascript" src="${ctx}/js/jubi/price-wave-notify-setting.js"></script>
</body>