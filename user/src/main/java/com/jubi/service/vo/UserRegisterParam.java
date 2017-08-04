/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户注册参数
 *
 * @author tjwang
 * @version $Id: UserRegisterParam.java, v 0.1 2017/3/22 0022 20:16 tjwang Exp $
 */
public class UserRegisterParam {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
