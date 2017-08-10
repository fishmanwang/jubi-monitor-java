package com.jubi.service.vo;

import java.util.List;

/**
 * 市场深度实时信息
 *
 * Created by Administrator on 2017/8/9.
 */
public class DepthRealVo {

    private String coin;

    private Double price;

    private List<List<Double>> asks;

    private List<List<Double>> bids;

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

    public List<List<Double>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<Double>> asks) {
        this.asks = asks;
    }

    public List<List<Double>> getBids() {
        return bids;
    }

    public void setBids(List<List<Double>> bids) {
        this.bids = bids;
    }
}
