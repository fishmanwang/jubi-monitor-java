/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.google.common.collect.Lists;
import com.jubi.RestResult;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.CommonErrorCode;
import com.jubi.service.TickerRateService;
import com.jubi.service.vo.TickerRateVo;
import com.jubi.util.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author tjwang
 * @version $Id: CoinRateController.java, v 0.1 2017/8/1 0001 15:37 tjwang Exp $
 */
@RestController
@RequestMapping("/rate")
public class TickerRateController {

    @Autowired
    private TickerRateService tickerRateService;

    @RequestMapping("/{coin}")
    public RestResult queryTickerRate(@PathVariable("coin") String coin, Integer span) {
        if (span == null) {
            span = 60;
        }
        return RestResult.ok(tickerRateService.queryTickerRate(coin, span));
    }

    @RequestMapping("/history")
    public RestResult queryHistoryTickerRate(String[] coins, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time) {
        if (coins.length > 10) {
            throw new ApplicationException(CommonErrorCode.PARAM_ERROR, "币数量不能大于10");
        }
        if (time == null) {
            time = new Date();
        }
        DateTime dateTime = new DateTime(time);
        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();

        List<TickerRateVo> result = Lists.newArrayList();
        for (String coin : coins) {
            result.addAll(tickerRateService.queryHistoryTickerRate(coin, year, month, day));
        }

        return RestResult.ok(result);
    }

    @RequestMapping("/recent")
    public RestResult queryRecentlyTickerRate(String[] coins) {
        if (coins == null || coins.length == 0) {
            return RestResult.ok();
        }
        int beginTime = DateUtils.getCurrentDayBeginTime();
        int size = coins.length;
        List<TickerRateVo> result = Lists.newArrayList();

        for (String coin : coins) {
            result.addAll(tickerRateService.queryRecentTickerRate(coin, size, beginTime));
        }

        return RestResult.ok(result);
    }

    @RequestMapping("/max/plus")
    public RestResult queryMaxPlusCoins() {
        List<TickerRateVo> rates = tickerRateService.queryMaxPlusCoins();
        List<String> coins = Lists.newArrayList();
        rates.forEach(p -> coins.add(p.getCoin()));
        return RestResult.ok(coins);
    }

    @RequestMapping("/max/minus")
    public RestResult queryMaxMunisCoins() {
        List<TickerRateVo> rates = tickerRateService.queryMaxMinusCoins();
        List<String> coins = Lists.newArrayList();
        rates.forEach(p -> coins.add(p.getCoin()));
        return RestResult.ok(coins);
    }
}
