/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

/**
 * @author tjwang
 * @version $Id: CoinOrderVo.java, v 0.1 2017/8/9 0009 10:06 tjwang Exp $
 */
public class CoinOrderVo {

    private Integer tid;

    private String coin;

    private Double price;

    private Double amount;

    private Integer tradeTime;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Integer tradeTime) {
        this.tradeTime = tradeTime;
    }
}
