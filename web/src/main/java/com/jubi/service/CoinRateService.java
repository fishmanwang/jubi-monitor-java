/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.dao.CoinRateDao;
import com.jubi.dao.entity.CoinRateEntity;
import com.jubi.service.vo.CoinRateVo;
import com.jubi.util.BeanMapperUtil;
import com.mybatis.domain.PageBounds;
import com.mybatis.domain.SortBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 涨幅服务
 * @author tjwang
 * @version $Id: CoinRateService.java, v 0.1 2017/8/1 0001 15:20 tjwang Exp $
 */
@Service
public class CoinRateService {

    @Autowired
    private CoinRateDao coinRateDao;

    public List<CoinRateVo> queryCoinRate(List<String> coins, int span) {
        Preconditions.checkArgument(coins != null && coins.size() > 0);

        if (span == 0) {
            span = 300; // 5分钟
        }

        List<CoinRateVo> result = Lists.newArrayList();

        PageBounds pb = new PageBounds(1, 2000, false);
        SortBy sy = SortBy.create("pk", SortBy.Direction.DESC.toString());
        pb.setOrders(Arrays.asList(sy));

        List<CoinRateEntity> ds = coinRateDao.queryCoinRates(coins, span, pb);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, CoinRateVo.class);
        return result;
    }

}
