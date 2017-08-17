/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

/**
 * @author tjwang
 * @version $Id: FavoriteCoin.java, v 0.1 2017/8/17 0017 17:42 tjwang Exp $
 */
public class FavoriteCoin {

    private String coin;

    private int priority;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
