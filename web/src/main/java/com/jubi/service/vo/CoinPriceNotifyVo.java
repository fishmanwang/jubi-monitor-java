/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 币，提示价格
 *
 * @author tjwang
 * @version $Id: CoinPriceNotifyVo.java, v 0.1 2017/8/24 0024 15:19 tjwang Exp $
 */
public class CoinPriceNotifyVo {

    private String coin;

    private String name;

    private List<Double> prices = Lists.newArrayList();

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

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }
}
