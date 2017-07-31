package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.dao.TickerDao;
import com.jubi.service.vo.TickerPriceVo;
import com.jubi.util.BeanMapperUtil;
import com.jubi.entity.TickerEntity;
import com.mybatis.domain.PageBounds;
import com.mybatis.domain.SortBy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
@Service
public class TickerService {

    @Autowired
    private TickerDao tickerDao;

    public List<TickerPriceVo> queryRecentlyTickers(String coin, int span) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin));

        if (span == 0) {
            span = 300; // 5分钟
        }

        List<TickerPriceVo> result = Lists.newArrayList();

        PageBounds pb = new PageBounds(1, 2000);
        SortBy sy = SortBy.create("pk", SortBy.Direction.DESC.toString());
        pb.setOrders(Arrays.asList(sy));

        List<TickerEntity> ds = tickerDao.queryTickers(coin, span, pb);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerPriceVo.class);
        return result;
    }



}
