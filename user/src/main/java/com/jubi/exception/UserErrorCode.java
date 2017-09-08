/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.exception;

/**
 * 用户模块异常，以100开头
 *
 * @author tjwang
 * @version $Id: UserErrorCode.java, v 0.1 2017/8/4 0004 16:31 tjwang Exp $
 */
public enum UserErrorCode implements ErrorCode {

    USER_NO_EXIST(1001, "用户名不存在"), USER_NAME_EMPTY(1002, "用户名不能为空"), USER_USERNAME_EXIST(1003, "用户名已经存在"), USER_MOBILE_ERROR(1004, "手机号码格式不正确"), USER_MOBILE_EXIST(1005, "手机号码已存在"), USER_DELETE_FAIL(
            1006,
            "删除用户失败"), USER_ACCOUNT_LOCKED(
            1007,
            "用户账号被锁定"), USER_PASSWORD_NULL(
            1008,
            "密码不能为空"), USER_PASSWORD_INCONSISTENT(
            1009,
            "密码和确认密码不一致"), USER_PASSWORD_ERROR(
            1010,
            "密码错误"), USER_PASSWORD_SAME_AS_OLD(
            1011,
            "新密码不能和旧密码一致"), USER_PASSWORD_SAME_AS_USERNAME(
            1012,
            "新密码不能和账号一致"), USER_PASSWORD_FORMAT_ERROR(
            1013,
            "密码格式不正确"), USER_PASSWORD_NOT_MATCH(
            1014,
            "用户名密码不匹配"), USER_PASSWORD_OLD_NULL(
            1015,
            "旧密码输入为空"), USER_PASSWORD_OLD_ERROR(
            1016,
            "旧密码输入错误"), USER_EMAIL_NO_EXIST(
            1017,
            "用户的邮箱不存在"), USER_EMAIL_FROMAT_ERROR(
            1018,
            "邮箱格式不正确"), USER_EMAIL_EXIST(
            1019,
            "邮箱已存在"),

    USER_NOT_LOGIN(1020, "用户未登录"),
    USER_AUTH_EORR(1021, "用户名/密码错误"),
    ACCOUNT_NOT_EXISTS(1100, "账户不存在"),
    USER_GRADE_NOT_EXISTS(1200, "用户等级不存在");

    private int status;
    private String message;

    UserErrorCode(int status, String errorMsg) {
        this.status = status;
        this.message = errorMsg;
    }

    @Override
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
