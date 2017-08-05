/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.common.Constants;
import com.jubi.dao.TickerRateDao;
import com.jubi.dao.entity.TickerRateEntity;
import com.jubi.dao.vo.TickerRateSpanParam;
import com.jubi.service.vo.TickerPriceVo;
import com.jubi.service.vo.TickerRateVo;
import com.jubi.util.BeanMapperUtil;
import com.jubi.util.DateUtils;
import com.mybatis.domain.PageBounds;
import com.mybatis.domain.SortBy;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 涨幅服务
 * @author tjwang
 * @version $Id: CoinRateService.java, v 0.1 2017/8/1 0001 15:20 tjwang Exp $
 */
@Service
public class TickerRateService {

    @Autowired
    private TickerRateDao tickerRateDao;

    @Autowired
    private TickerService tickerService;

    @Cacheable(value = "ticker-rate", keyGenerator = "defaultKeyGenerator")
    public List<TickerRateVo> queryTickerRate(String coin, int span) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");

        List<TickerRateVo> result = Lists.newArrayList();

        Date now = new Date();
        int end = Long.valueOf(now.getTime() / 1000).intValue();
        if (span < 60) {
            span = 60; // 1分钟
        }

        TickerRateSpanParam param = new TickerRateSpanParam();
        param.setCoins(Arrays.asList(coin));
        param.setSpan(span);
        param.setEnd(end);

        PageBounds pb = new PageBounds(1, Constants.PAGE_COUNT_LIMIT, false);
        pb.setOrders(Arrays.asList(SortBy.create("pk", "desc")));

        List<TickerRateEntity> ds = null;
        if (span <= 3600) {
            ds = tickerRateDao.queryTickerRates(param, pb);
        } else {
            ds = tickerRateDao.queryHourTickerRates(param, pb);
        }

        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerRateVo.class);
        return result;

    }

    /**
     * 查询最新的涨幅
     * @param coins
     * @return
     */
    //@Cacheable(value = "ticker-rate-history", keyGenerator = "defaultKeyGenerator")
    public List<TickerRateVo> queryHistoryTickerRate(List<String> coins, int year, int month, int day) {
        Preconditions.checkArgument(coins != null && coins.size() > 0);

        int span = 60 * 10; // 十分钟
        PageBounds pb = new PageBounds(1, 2000, false);
        SortBy sy = SortBy.create("pk", SortBy.Direction.DESC.toString());
        pb.setOrders(Arrays.asList(sy));

        Date date = new DateTime(year, month, day, 0, 0, 0).toDate();

        Integer start = DateUtils.getDayBeginTime(date);
        Integer end = start + Constants.DAY_LONG;

        return queryTickerRate(coins, span, start, end);
    }

    /**
     * 查询最新的涨幅
     * @param coins
     * @return
     */
    public List<TickerRateVo> queryRecentTickerRate(List<String> coins) {
        Preconditions.checkArgument(coins != null && coins.size() > 0);

        List<TickerRateVo> result = Lists.newArrayList();

        int beginTime = DateUtils.getCurrentDayBeginTime();
        Optional<TickerPriceVo> tickerOpt = tickerService.queryLastTicker(coins.get(0));
        if (!tickerOpt.isPresent()) {
            return result;
        }

        int end = tickerOpt.get().getPk();
        if (end < beginTime) {
            return result;
        }

        int span = inferSpan(end - beginTime, coins.size());
        result = queryTickerRate(coins, span, beginTime, end);

        return result;
    }

    public List<TickerRateVo> queryRankedTickerRate() {
        Integer lastPk = tickerRateDao.queryLastPk();
        List<TickerRateEntity> ds = tickerRateDao.queryRankedTickerRate(lastPk);
        return BeanMapperUtil.mapList(ds, TickerRateVo.class);
    }

    private List<TickerRateVo> queryTickerRate(List<String> coins, int span, Integer start, Integer end) {
        Preconditions.checkArgument(coins != null && coins.size() > 0);

        if (span == 0) {
            span = 300; // 5分钟
        }

        List<TickerRateVo> result = Lists.newArrayList();

        PageBounds pb = new PageBounds(1, 2000, false);
        SortBy sy = SortBy.create("pk", SortBy.Direction.DESC.toString());
        pb.setOrders(Arrays.asList(sy));

        TickerRateSpanParam param = new TickerRateSpanParam();
        param.setCoins(coins);
        param.setSpan(span);
        param.setStart(start);
        param.setEnd(end);
        List<TickerRateEntity> ds = tickerRateDao.queryTickerRates(param, pb);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerRateVo.class);
        return result;
    }

    /**
     * 推断间隔
     * @param timeSpan : 时间间隔
     * @param count : 币种类数量
     * @return
     */
    private int inferSpan(int timeSpan, int count) {
        int[] cadidates = { 60, 60 * 5, 60 * 10, 60 * 30, 60 * 60, 60 * 60 * 8, 60 * 60 * 24 };
        // 单个币最大展示数量
        int maxCount = Constants.PAGE_COUNT_LIMIT / count;
        int rawSpan = timeSpan / maxCount;
        for (int c : cadidates) {
            if (rawSpan < c) {
                return c;
            }
        }
        return cadidates[cadidates.length - 1];
    }
}
