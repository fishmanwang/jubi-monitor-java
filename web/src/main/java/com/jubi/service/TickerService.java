package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.dao.TickerDao;
import com.jubi.dao.TickerExtDao;
import com.jubi.dao.entity.TickerEntity;
import com.jubi.dao.entity.TickerEntityExample;
import com.jubi.dao.vo.TickerSpanParam;
import com.jubi.service.vo.TickerPriceVo;
import com.jubi.util.BeanMapperUtil;
import com.jubi.util.DateUtils;
import com.mybatis.domain.PageBounds;
import com.mybatis.domain.SortBy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
@Service
public class TickerService {

    @Autowired
    private TickerExtDao tickerExtDao;

    public List<TickerPriceVo> queryRecentlyTickers(String coin, int span) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin));

        if (span == 0) {
            span = 300; // 5分钟
        }

        List<TickerPriceVo> result = Lists.newArrayList();

        PageBounds pb = new PageBounds(1, 2000);
        SortBy sy = SortBy.create("pk", SortBy.Direction.DESC.toString());
        pb.setOrders(Arrays.asList(sy));


        Date now = new Date();
        int beginTime = DateUtils.getDayBeginTime(now);
        int end = Long.valueOf(now.getTime() / 1000).intValue();

        TickerSpanParam param = new TickerSpanParam();
        param.setCoin(coin);
        param.setSpan(span);
        param.setStart(beginTime);
        param.setEnd(end);

        List<TickerEntity> ds = queryTickers(param);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerPriceVo.class);
        return result;
    }

    private List<TickerEntity> queryTickers(TickerSpanParam param) {
        if (param.getSpan() < 60) {
            param.setSpan(60);
        }

        PageBounds pb = new PageBounds(1, 3000);
        SortBy sy = SortBy.create("pk", SortBy.Direction.ASC.toString());
        pb.setOrders(Arrays.asList(sy));

        return tickerExtDao.queryTickers(param, pb);
    }



}
