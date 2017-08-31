/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

import com.jubi.exception.CommonErrorCode;
import com.jubi.util.ValidateUtil;

/**
 * 价格
 *
 * @author tjwang
 * @version $Id: CoinPriceWaveVo.java, v 0.1 2017/8/31 0031 13:20 tjwang Exp $
 */
public class CoinPriceWaveVo {

    /**
     * 币
     */
    private String coin;

    /**
     * 间隔
     */
    private Integer span;

    /**
     * 比率
     */
    private Double rate;

    public void validate() {
        ValidateUtil.checkBlank(coin, CommonErrorCode.PARAM_ERROR, "币不能为空");
        ValidateUtil.checkNull(span, CommonErrorCode.PARAM_ERROR, "间隔不能为空");
        ValidateUtil.checkNull(rate, CommonErrorCode.PARAM_ERROR, "比率不能为空");
        ValidateUtil.checkCondition(span >= 1 && span <= 60, CommonErrorCode.PARAM_ERROR, "间隔必须间于1-60");
        ValidateUtil.checkCondition(rate >= 1 && rate <= 100, CommonErrorCode.PARAM_ERROR, "比率必须间于1-100");
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
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
}
