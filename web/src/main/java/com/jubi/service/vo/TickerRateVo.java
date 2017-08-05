/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

/**
 *
 * @author tjwang
 * @version $Id: TickerRateVo.java, v 0.1 2017/8/1 0001 15:20 tjwang Exp $
 */
public class TickerRateVo {

    /**
     * 时间
     */
    private Integer pk;

    /**
     * 币
     */
    private String  coin;

    /**
     * 币名称
     */
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
