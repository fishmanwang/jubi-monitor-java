/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: PriceNotifyVo.java, v 0.1 2017/8/24 0024 15:20 tjwang Exp $
 */
public class PriceNotifyVo {

    private Integer userId;

    private List<CoinPriceNotifyVo> vos = Lists.newArrayList();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<CoinPriceNotifyVo> getVos() {
        return vos;
    }

    public void setVos(List<CoinPriceNotifyVo> vos) {
        this.vos = vos;
    }
}
