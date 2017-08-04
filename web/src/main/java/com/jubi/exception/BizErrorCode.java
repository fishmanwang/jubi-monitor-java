/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.exception;

/**
 * 业务异常，以9开头
 *
 * @author tjwang
 * @version $Id: BizErrorCode.java, v 0.1 2017/8/4 0004 17:43 tjwang Exp $
 */
public enum BizErrorCode implements ErrorCode {
    USER_NAME_EMPTY(9001, "用户名不能为空"),
    USER_PASSWORD_NULL(9002, "密码不能为空"),
    VALIDATION_ERROR(9003, "验证码不正确");

    private int status;
    private String message;

    private BizErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
