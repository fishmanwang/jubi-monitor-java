/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

/**
 * @author tjwang
 * @version $Id: DepthVo.java, v 0.1 2017/8/7 0007 10:21 tjwang Exp $
 */
public class DepthVo {

    private Integer pk;

    private String coin;

    private Double price;

    private String asks;

    private String bids;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
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

    public String getAsks() {
        return asks;
    }

    public void setAsks(String asks) {
        this.asks = asks;
    }

    public String getBids() {
        return bids;
    }

    public void setBids(String bids) {
        this.bids = bids;
    }
}
