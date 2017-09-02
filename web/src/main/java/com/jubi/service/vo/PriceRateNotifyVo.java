package com.jubi.service.vo;

/**
 * Created by Administrator on 2017/9/2.
 */
public class PriceRateNotifyVo {

    private String coin;

    private Integer rate;

    public void validate() {

    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
