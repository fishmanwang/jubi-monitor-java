/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.common.Constants;
import com.jubi.dao.CoinRateDao;
import com.jubi.dao.entity.CoinRateEntity;
import com.jubi.dao.vo.CoinRateSpanParam;
import com.jubi.service.vo.CoinRateVo;
import com.jubi.service.vo.TickerPriceVo;
import com.jubi.util.BeanMapperUtil;
import com.jubi.util.DateUtils;
import com.mybatis.domain.PageBounds;
import com.mybatis.domain.SortBy;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CoinRateService {

    @Autowired
    private CoinRateDao   coinRateDao;

    @Autowired
    private TickerService tickerService;

    /**
     * 查询最新的涨幅
     * @param coins
     * @return
     */
    public List<CoinRateVo> queryHistoryCoinRate(List<String> coins, Date date) {
        Preconditions.checkArgument(coins != null && coins.size() > 0);

        int span = 60 * 10; // 十分钟
        PageBounds pb = new PageBounds(1, 2000, false);
        SortBy sy = SortBy.create("pk", SortBy.Direction.DESC.toString());
        pb.setOrders(Arrays.asList(sy));

        Integer start = DateUtils.getDayBeginTime(date);
        Integer end = start + Constants.DAY_LONG;

        return queryCoinRate(coins, span, start, end);
    }

    /**
     * 查询最新的涨幅
     * @param coins
     * @return
     */
    public List<CoinRateVo> queryRecentCoinRate(List<String> coins) {
        Preconditions.checkArgument(coins != null && coins.size() > 0);

        List<CoinRateVo> result = Lists.newArrayList();

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
        result = queryCoinRate(coins, span, beginTime, end);

        return result;
    }

    private List<CoinRateVo> queryCoinRate(List<String> coins, int span, Integer start, Integer end) {
        Preconditions.checkArgument(coins != null && coins.size() > 0);

        if (span == 0) {
            span = 300; // 5分钟
        }

        List<CoinRateVo> result = Lists.newArrayList();

        PageBounds pb = new PageBounds(1, 2000, false);
        SortBy sy = SortBy.create("pk", SortBy.Direction.DESC.toString());
        pb.setOrders(Arrays.asList(sy));

        CoinRateSpanParam param = new CoinRateSpanParam();
        param.setCoins(coins);
        param.setSpan(span);
        param.setStart(start);
        param.setEnd(end);
        List<CoinRateEntity> ds = coinRateDao.queryCoinRates(param, pb);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, CoinRateVo.class);
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
