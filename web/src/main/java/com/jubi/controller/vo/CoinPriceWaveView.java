/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller.vo;

/**
 * @author tjwang
 * @version $Id: CoinPriceWaveView.java, v 0.1 2017/8/31 0031 17:02 tjwang Exp $
 */
public class CoinPriceWaveView {

    /**
     * 币
     */
    private String coin;

    /**
     * 名称
     */
    private String name;

    /**
     * 间隔
     */
    private Integer span;

    /**
     * 比率
     */
    private Double rate;

    /**
     * 是否设置
     */
    private boolean setted;

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

    public Integer getSpan() {
        return span;
    }

    public void setSpan(Integer span) {
        this.span = span;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public boolean isSetted() {
        return setted;
    }

    public void setSetted(boolean setted) {
        this.setted = setted;
    }
}
