package com.jubi.dao.entity;

public class DepthWithBLOBs extends Depth {
    private String asks;

    private String bids;

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