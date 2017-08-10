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
import com.jubi.dao.entity.CoinOrderEntity;
import com.jubi.dao.entity.CoinOrderEntityExample;
import com.jubi.service.vo.CoinOrderVo;
import com.jubi.util.BeanMapperUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinOrderService {

    @Autowired
    private CoinOrderDao coinOrderDao;

    /**
     * 获取最近两小时内(上一小时+当前小时)所有交易信息
     *
     * @param coin
     * @return
     */
    public List<CoinOrderVo> queryRecentOrders(String coin) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "虚拟币不能为空");

        DateTime dateTime = new DateTime();
        dateTime = dateTime.withMillisOfSecond(0);
        dateTime = dateTime.withSecondOfMinute(0);
        dateTime = dateTime.withMinuteOfHour(0);

        System.out.println(dateTime.getMillis());

        List<CoinOrderVo> result;

        int beginTime = Long.valueOf(dateTime.getMillis() / 1000).intValue();
        CoinOrderEntityExample exam = new CoinOrderEntityExample();
        exam.createCriteria().andTradeTimeGreaterThanOrEqualTo(beginTime);

        List<CoinOrderEntity> ds = coinOrderDao.selectByExample(exam);

//        ConvertUtils.register(new Converter() {
//
//            @Override
//            public Object convert(Class aClass, Object o) {
//                return new Date(Long.valueOf((Integer) o) * 1000);
//            }
//        }, Date.class);

        result = BeanMapperUtil.mapList(ds, CoinOrderVo.class);

//        ConvertUtils.deregister(Date.class);


        return result;
    }

}
