package com.jubi.service;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.common.Constants;
import com.jubi.dao.TickerDao;
import com.jubi.dao.TickerExtDao;
import com.jubi.dao.entity.TickerEntity;
import com.jubi.dao.entity.TickerEntityExample;
import com.jubi.dao.vo.TickerSpanParam;
import com.jubi.service.vo.CoinVo;
import com.jubi.service.vo.TickerPriceVo;
import com.jubi.service.vo.TickerVo;
import com.jubi.util.BeanMapperUtil;
import com.jubi.util.DateUtils;
import com.jubi.util.ObjectMapperUtil;
import com.jubi.util.RedisCacheUtil;
import com.mybatis.domain.PageBounds;
import com.mybatis.domain.SortBy;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
    private TickerDao tickerDao;

    @Autowired
    private TickerExtDao tickerExtDao;

    @Autowired
    private CoinService coinService;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Cacheable(value = "ticker", keyGenerator = "defaultKeyGenerator")
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

        PageBounds pb = new PageBounds(1, Constants.PAGE_COUNT_LIMIT, false);
        pb.setOrders(Arrays.asList(SortBy.create("pk", "desc")));

        List<TickerEntity> ds = null;
        if (span <= 3600) {
            ds = tickerExtDao.queryTickers(param, pb);
        } else {
            ds = tickerExtDao.queryHourTickers(param, pb);
        }

        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerPriceVo.class);
        return result;
    }

    /**
     * 获取当前所有行情
     *
     * @return
     */
    public List<TickerVo> getRecentTickers() {
        List<TickerVo> list = Lists.newArrayList();
        ValueOperations ops = stringRedisTemplate.opsForValue();

        List<CoinVo> coins = coinService.getAllCoins();
        for (CoinVo c : coins) {
            String coin = c.getCode();
            String key = "cache_ticker_" + coin;
            String str = (String) ops.get(key);
            System.out.println("coin cache : " + str);
            if (StringUtils.isNotBlank(str)) {
                list.add(ObjectMapperUtil.read(str, TickerVo.class));
            }
        }
        return list;
    }

    /**
     * 查询当日行情
     *
     * @param coin
     * @return
     */
    @Cacheable(value = "ticker", keyGenerator = "defaultKeyGenerator")
    public List<TickerPriceVo> queryRecentlyTickers(String coin) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");

        List<TickerPriceVo> result = Lists.newArrayList();

        Date now = new Date();
        int beginTime = DateUtils.getDayBeginTime(now);
        int span = 60; // 1分钟

        TickerSpanParam param = new TickerSpanParam();
        param.setCoin(coin);
        param.setSpan(span);
        param.setStart(beginTime);

        PageBounds pb = new PageBounds(1, Constants.PAGE_COUNT_LIMIT, false);
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
     *
     * @param coin
     * @param year
     * @param month
     * @param day
     * @return
     */
    @Cacheable(value = "ticker-history", keyGenerator = "defaultKeyGenerator")
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

        PageBounds pb = new PageBounds(1, Constants.PAGE_COUNT_LIMIT, false);
        pb.setOrders(Arrays.asList(SortBy.create("pk", "asc")));

        List<TickerEntity> ds = tickerExtDao.queryTickers(param, pb);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerPriceVo.class);
        return result;
    }

    /**
     * 获取最后的价格
     *
     * @param coin
     * @return
     */
    public Optional<TickerPriceVo> queryLastTicker(String coin) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");

        TickerEntityExample exam = new TickerEntityExample();
        exam.createCriteria().andCoinEqualTo(coin);
        exam.setOrderByClause("pk desc");
        PageBounds pb = new PageBounds(1, 1, false);
        List<TickerEntity> ds = tickerDao.selectByExampleWithPageBounds(exam, pb);
        if (ds.size() == 0) {
            return Optional.absent();
        }
        TickerEntity t = ds.get(0);
        TickerPriceVo vo = BeanMapperUtil.map(t, TickerPriceVo.class);
        return Optional.of(vo);
    }

    /**
     * 获取所有币的最新价格
     *
     * @return
     */
    public Double queryLastTickerPrice(String coin) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");
        int ct = Long.valueOf(System.currentTimeMillis() / 1000).intValue();
        return doQueryPriceAtPk(coin, ct);
    }

    /**
     * 获取指定时间的价格
     *
     * @param coin
     * @param pk
     * @return
     */
    @Cacheable(value = "ticker-history", keyGenerator = "defaultKeyGenerator")
    public Double queryPriceAtPk(String coin, Integer pk) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");
        Preconditions.checkArgument(pk != null && pk > 0, "非法时间");
        return doQueryPriceAtPk(coin, pk);
    }

    /**
     * 获取指定时间的价格
     *
     * @param coin
     * @param pk
     * @return
     */
    private Double doQueryPriceAtPk(String coin, Integer pk) {
        TickerEntityExample exam = new TickerEntityExample();
        exam.createCriteria().andCoinEqualTo(coin).andPkLessThanOrEqualTo(pk);
        exam.setOrderByClause("pk desc");
        PageBounds pb = new PageBounds(1, 1, false);
        List<TickerEntity> ds = tickerDao.selectByExampleWithPageBounds(exam, pb);
        if (ds.size() == 0) {
            return 0d;
        }
        return ds.get(0).getPrice();
    }

}
