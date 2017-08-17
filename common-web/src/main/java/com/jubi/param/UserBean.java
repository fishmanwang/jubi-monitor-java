/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.param;

/**
 * 用户信息。登录后存在缓存中的信息。
 *
 * @author tjwang
 * @version $Id: UserBean.java, v 0.1 2017/3/23 0023 14:41 tjwang Exp $
 */
public class UserBean {

    private Integer id;

    private String username;

    private String nickname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
