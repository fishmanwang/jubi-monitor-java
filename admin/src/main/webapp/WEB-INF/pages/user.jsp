<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<style>
    #userTable td {
        padding-top: 10px;
    }
</style>

<tag:layout title="用户管理">

    <div id="content">
        <div>
            <div style="float: left;">
                用户名：<input id="usernameInput" style="width: 120px;"/>
                &nbsp;昵称：<input id="nicknameInput" style="width: 120px;"/>
            </div>
            <div style="float: left;margin-left: 10px;">
                <input type="button" id="searchBtn" value="查询"/>
            </div>
            <div style="clear:both;"></div>
        </div>
        <div>
            <table id="userTable" width="800px">
                <tr class="head">
                    <td>用户名</td>
                    <td>昵称</td>
                    <td>电话</td>
                    <td>邮箱</td>
                    <td>上次登录IP</td>
                    <td>上次登录时间</td>
                    <td>操作</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="box">
        <div id="pagination" class="page fl"></div>
    </div>

</tag:layout>

<script type="text/javascript" src="${ctx}/js/jubi/user.js"></script>