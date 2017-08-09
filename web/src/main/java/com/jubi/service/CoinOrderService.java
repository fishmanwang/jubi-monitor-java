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
import com.google.common.collect.Lists;
import com.jubi.dao.CoinOrderDao;
import com.jubi.dao.entity.CoinOrderEntity;
import com.jubi.dao.entity.CoinOrderEntityExample;
import com.jubi.service.vo.CoinOrderVo;
import com.jubi.util.BeanMapperUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CoinOrderService {

    @Autowired
    private CoinOrderDao coinOrderDao;

    /**
     * 获取当天所有交易信息
     *
     * @param coin
     * @param time : 开始时间
     * @return
     */
    public List<CoinOrderVo> queryOrders(String coin, Date time) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "虚拟币不能为空");

        if (time == null) {
            time = new Date();
        }

        List<CoinOrderVo> result = Lists.newArrayList();

        int beginTime = Long.valueOf(time.getTime() / 1000).intValue();
        CoinOrderEntityExample exam = new CoinOrderEntityExample();
        exam.createCriteria().andTradeTimeGreaterThanOrEqualTo(beginTime);

        List<CoinOrderEntity> ds = coinOrderDao.selectByExample(exam);
        for (CoinOrderEntity d : ds) {
            CoinOrderVo vo = BeanMapperUtil.map(d, CoinOrderVo.class);
            vo.setTradeTime(new Date(d.getTradeTime() * 1000));
            result.add(vo);
        }

        return result;
    }

}
