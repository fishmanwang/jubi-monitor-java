/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller.vo;

/**
 * @author tjwang
 * @version $Id: CoinPriceInfo.java, v 0.1 2017/8/14 0014 18:19 tjwang Exp $
 */
public class CoinPriceInfo {

    /**
     * 币
     */
    private String coin;

    /**
     * 币名
     */
    private String name;

    /**
     * 当前价格
     */
    private Double price;

    /**
     * 涨幅
     */
    private Double rate;

    /**
     * 三天涨幅
     */
    private Double threeRate;

    /**
     * 七天涨幅
     */
    private Double sevenRate;

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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getThreeRate() {
        return threeRate;
    }

    public void setThreeRate(Double threeRate) {
        this.threeRate = threeRate;
    }

    public Double getSevenRate() {
        return sevenRate;
    }

    public void setSevenRate(Double sevenRate) {
        this.sevenRate = sevenRate;
    }
}
