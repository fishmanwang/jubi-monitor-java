/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

/**
 * @author tjwang
 * @version $Id: AccountVo.java, v 0.1 2017/8/22 0022 14:27 tjwang Exp $
 */
public class AccountVo {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
