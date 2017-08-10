/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

/**
 * 虚拟币交易服务
 *
 * @author tjwang
 * @version $Id: CoinOrderService.java, v 0.1 2017/8/9 0009 9:59 tjwang Exp $
 */

import com.google.common.base.Preconditions;
import com.jubi.dao.CoinOrderDao;
import com.jubi.dao.CoinOrderExtDao;
import com.jubi.dao.vo.CoinOrderNumVo;
import com.jubi.service.vo.CoinOrderStatisticVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinOrderService {

    @Autowired
    private CoinOrderDao coinOrderDao;

    @Autowired
    private CoinOrderExtDao coinOrderExtDao;

    /**
     * 获取交易信息
     *
     * @param coin
     * @return
     */
    public CoinOrderStatisticVo queryOrderStatistics(String coin, int time) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "虚拟币不能为空");
        Preconditions.checkArgument(time > 0, "时间必须大于0");

        CoinOrderStatisticVo result = new CoinOrderStatisticVo();

        CoinOrderNumVo plusVo = coinOrderExtDao.queryStatistic(coin, time, true);
        CoinOrderNumVo minusVo = coinOrderExtDao.queryStatistic(coin, time, false);

        int count = plusVo.getCount() + minusVo.getCount();
        long buyCount = plusVo.getCount();
        long buyAmount = plusVo.getAmount();
        long buyTotal = plusVo.getTotal();
        long sellCount = minusVo.getCount();
        long sellAmount = minusVo.getAmount();
        long sellTotal = minusVo.getTotal();
        result.setBuyCount(buyCount);
        result.setBuyAmount(buyAmount);
        result.setBuyTotal(buyTotal);
        result.setSellCount(sellCount);
        result.setSellAmount(sellAmount);
        result.setSellTotal(sellTotal);

        return result;
    }

}
