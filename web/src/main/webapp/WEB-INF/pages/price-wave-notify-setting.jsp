<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<tag:layout title="价格波动提醒配置">
    <div id="main">
        <div align="center">
            <b>使用方法：</b>
        <span style="color:orangered; font-size: 16px;">
            请先在 <a href="${ctx}/page/account/admin.html">账户管理</a> 中设置关注的虚拟币
        </span>，然后选择虚拟币，填写间隔(1-60整数)和比率(1-100两位小数)，点击
            <b>保存</b>。
        </div>
        <div id="configArea" style="width:800px; margin-top: 20px">
            <div>
                <select id="coinSel">
                    <option value="">请选择虚拟币</option>
                    <c:forEach items="${items}" var="item">
                        <option value="${item.coin}">${item.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <c:forEach items="${items}" var="item">
                    <div class="settingDiv" coin="${item.coin}" style="display: none; margin: 15px 0;"
                         setted="${item.setted}">
                        <div style="float: left;display: inline-block; width: 120px;">
                            <span>${item.name} <span style="color:#999;">${item.coin}</span></span>
                        </div>
                        <div style="float: left">
                            间隔&nbsp;<input class="spanInput" value="${item.span}" style="width:60px"/>&nbsp;
                            分钟内波动&nbsp;<input class="rateInput" value="${item.rate}" style="width:60px"/>&nbsp;%&nbsp;提醒
                        </div>
                        <div style="margin-left: 20px; float: left;">
                            <input type="button" class="delSetting" coin="${item.coin}" value="删除"/>
                        </div>
                        <div style="clear:both"></div>
                    </div>
                </c:forEach>
            </div>

            <div style="margin-top: 10px">
                <input type="button" value="保存" id="saveBtn"/>
                <input type="button" value="取消" onclick="window.location.href='${ctx}/page/index.html'"/>
            </div>
            <p style="color:red; font-size: 12px;">注意：您的等级为
                <span style="color: green;">L<shiro:principal property="grade"/></span> 每天最多接收
                <span style="color: green;"><shiro:principal property="emailCount"/></span>
                封邮件，请合理设置监控的虚拟币和波动。</p>
        </div>
    </div>
</tag:layout>

<script type="text/javascript" src="${ctx}/js/jubi/price-wave-notify-setting.js"></script>