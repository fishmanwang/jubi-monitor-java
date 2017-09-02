package com.jubi.controller.vo;

import com.jubi.exception.ApplicationException;
import com.jubi.exception.CommonErrorCode;
import com.jubi.util.ValidateUtil;

import java.util.Set;

/**
 * Created by Administrator on 2017/9/2.
 */
public class PriceRateNotifyView {

    private String coin;

    private String name;

    private Integer rate;

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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
