/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller.vo;

/**
 * @author tjwang
 * @version $Id: CoinPriceNotifyViewVo.java, v 0.1 2017/8/25 0025 16:31 tjwang Exp $
 */
public class CoinPriceNotifyViewVo {

    private String coin;

    private String name;

    private Double price;

    private int priority;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
