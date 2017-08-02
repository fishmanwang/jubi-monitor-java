package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.common.Constants;
import com.jubi.dao.TickerExtDao;
import com.jubi.dao.entity.TickerEntity;
import com.jubi.dao.vo.TickerSpanParam;
import com.jubi.service.vo.TickerPriceVo;
import com.jubi.util.BeanMapperUtil;
import com.jubi.util.DateUtils;
import com.mybatis.domain.PageBounds;
import com.mybatis.domain.SortBy;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
@Service
public class TickerService {

    private static final int LIMIT = 2000;

    @Autowired
    private TickerExtDao tickerExtDao;

    public List<TickerPriceVo> queryTickers(String coin, Integer span) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");

        List<TickerPriceVo> result = Lists.newArrayList();

        Date now = new Date();
        int end = Long.valueOf(now.getTime() / 1000).intValue();
        if (span < 60) {
            span = 60; // 1分钟
        }

        TickerSpanParam param = new TickerSpanParam();
        param.setCoin(coin);
        param.setSpan(span);
        param.setEnd(end);

        PageBounds pb = new PageBounds(1, LIMIT, false);
        pb.setOrders(Arrays.asList(SortBy.create("pk", "desc")));

        List<TickerEntity> ds = tickerExtDao.queryTickers(param, pb);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerPriceVo.class);
        return result;
    }

    /**
     * 查询当日行情
     * @param coin
     * @return
     */
    public List<TickerPriceVo> queryRecentlyTickers(String coin) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");

        List<TickerPriceVo> result = Lists.newArrayList();

        Date now = new Date();
        int beginTime = DateUtils.getDayBeginTime(now);
        int end = Long.valueOf(now.getTime() / 1000).intValue();
        int span = 60; // 1分钟

        TickerSpanParam param = new TickerSpanParam();
        param.setCoin(coin);
        param.setSpan(span);
        param.setStart(beginTime);

        PageBounds pb = new PageBounds(1, LIMIT, false);
        pb.setOrders(Arrays.asList(SortBy.create("pk", "asc")));

        List<TickerEntity> ds = tickerExtDao.queryTickers(param, pb);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerPriceVo.class);
        return result;
    }

    /**
     * 获取指定日历史行情
     * @param coin
     * @param year
     * @param month
     * @param day
     * @return
     */
    public List<TickerPriceVo> queryHistoryTickers(String coin, int year, int month, int day) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");

        List<TickerPriceVo> result = Lists.newArrayList();

        DateTime time = new DateTime(year, month, day, 0, 0, 0);
        int begin = Long.valueOf(time.getMillis() / 1000).intValue();
        int end = begin + Constants.DAY_LONG - 1;
        int span = 60;

        TickerSpanParam param = new TickerSpanParam();
        param.setCoin(coin);
        param.setSpan(span);
        param.setStart(begin);
        param.setEnd(end);

        PageBounds pb = new PageBounds(1, LIMIT, false);
        pb.setOrders(Arrays.asList(SortBy.create("pk", "asc")));

        List<TickerEntity> ds = tickerExtDao.queryTickers(param, pb);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerPriceVo.class);
        return result;
    }

}
