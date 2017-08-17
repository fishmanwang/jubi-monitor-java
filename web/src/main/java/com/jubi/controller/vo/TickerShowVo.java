/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller.vo;

/**
 * 行情展示VO
 *
 * @author tjwang
 * @version $Id: TickerShowVo.java, v 0.1 2017/8/17 0017 11:32 tjwang Exp $
 */
public class TickerShowVo {
    /**
     * 币
     */
    private String coin;

    /**
     * 币名称
     */
    private String name;

    /**
     * 时间
     */
    private Integer pk;

    /**
     * 最高价
     */
    private Double high;

    /**
     * 最低价
     */
    private Double low;

    /**
     * 买一价
     */
    private Double buy;

    /**
     * 卖一价
     */
    private Double sell;

    /**
     * 最后一次成交价
     */
    private Double last;

    /**
     * 成交量
     */
    private Double vol;

    /**
     * 成交额
     */
    private Double volume;

    /**
     * 当天开盘价
     */
    private Double beginPrice;

    /**
     * 三天前价格
     */
    private Double threePrice;

    /**
     * 七天前价格
     */
    private Double sevenPrice;

    /**
     * 半个月前价格
     */
    private Double fiftyPrice;

    /**
     * 一个月前价格
     */
    private Double monthPrice;

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

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(Double beginPrice) {
        this.beginPrice = beginPrice;
    }

    public Double getThreePrice() {
        return threePrice;
    }

    public void setThreePrice(Double threePrice) {
        this.threePrice = threePrice;
    }

    public Double getSevenPrice() {
        return sevenPrice;
    }

    public void setSevenPrice(Double sevenPrice) {
        this.sevenPrice = sevenPrice;
    }

    public Double getFiftyPrice() {
        return fiftyPrice;
    }

    public void setFiftyPrice(Double fiftyPrice) {
        this.fiftyPrice = fiftyPrice;
    }

    public Double getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(Double monthPrice) {
        this.monthPrice = monthPrice;
    }
}
