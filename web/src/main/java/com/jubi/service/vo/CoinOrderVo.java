/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author tjwang
 * @version $Id: CoinOrderVo.java, v 0.1 2017/8/9 0009 10:06 tjwang Exp $
 */
public class CoinOrderVo {

    private Integer tid;

    private String coin;

    private Double price;

    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tradeTime;

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

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }
}
