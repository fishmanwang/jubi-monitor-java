/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.dao.entity;

/**
 *
 * @author tjwang
 * @version $Id: TickerRateEntity.java, v 0.1 2017/8/1 0001 15:15 tjwang Exp $
 */
public class TickerRateEntity {

    private Integer id;

    private Integer pk;

    private String  coin;

    private Double  rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
