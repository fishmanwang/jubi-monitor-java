/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.service.CoinOrderService;
import com.jubi.service.vo.CoinOrderStatisticVo;
import com.jubi.service.vo.CoinOrderVo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: CoinOrderController.java, v 0.1 2017/8/9 0009 11:10 tjwang Exp $
 */
@RestController
@RequestMapping("/coin/order")
public class CoinOrderController {

    @Autowired
    private CoinOrderService coinOrderService;

    /**
     * 查询最近十分钟交易情况
     * @param coin
     * @return
     */
    @RequestMapping(value = "/tm/{coin}", method = RequestMethod.GET)
    public RestResult queryLastTenMinutesCoinOrderStatistic(@PathVariable("coin") String coin) {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withMillisOfSecond(0).withSecondOfMinute(0).plusMinutes(-10);
        int time = Long.valueOf(dateTime.getMillis() / 1000).intValue();

        CoinOrderStatisticVo vo = coinOrderService.queryOrderStatistics(coin, time);
        return RestResult.ok(vo);
    }

    /**
     * 查询最近十分钟交易情况
     * @param coin
     * @return
     */
    @RequestMapping(value = "/oh/{coin}", method = RequestMethod.GET)
    public RestResult queryLastHourCoinOrderStatistic(@PathVariable("coin") String coin) {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0).plusHours(-1);
        int time = Long.valueOf(dateTime.getMillis() / 1000).intValue();

        CoinOrderStatisticVo vo = coinOrderService.queryOrderStatistics(coin, time);
        return RestResult.ok(vo);
    }

}
