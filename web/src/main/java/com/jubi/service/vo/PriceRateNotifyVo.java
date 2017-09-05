package com.jubi.service.vo;

import com.jubi.exception.ApplicationException;
import com.jubi.exception.CommonErrorCode;
import com.jubi.util.ValidateUtil;
import org.apache.commons.lang3.Validate;

import java.util.Set;

/**
 * Created by Administrator on 2017/9/2.
 */
public class PriceRateNotifyVo {

    private String coin;

    private Integer rate;

    public void validate() {
        ValidateUtil.checkBlank(coin, CommonErrorCode.PARAM_ERROR, "币不能为空");
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
