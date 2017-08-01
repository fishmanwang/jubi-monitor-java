/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

/**
 *
 * @author tjwang
 * @version $Id: CoinRateVo.java, v 0.1 2017/8/1 0001 15:20 tjwang Exp $
 */
public class CoinRateVo {

    /**
     * 时间
     */
    private Integer pk;

    /**
     * 币
     */
    private String  coin;

    /**
     * 涨幅
     */
    private Double  rate;

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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
