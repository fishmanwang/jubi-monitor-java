package com.jubi.dao.entity;

public class CoinOrderEntity {
    private Integer id;

    private Integer tid;

    private String coin;

    private Double price;

    private Double amount;

    private Integer tradeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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