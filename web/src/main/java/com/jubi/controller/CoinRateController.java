/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.CommonErrorCode;
import com.jubi.service.CoinRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author tjwang
 * @version $Id: CoinRateController.java, v 0.1 2017/8/1 0001 15:37 tjwang Exp $
 */
@RestController
@RequestMapping("/rate")
public class CoinRateController {

    @Autowired
    private CoinRateService coinRateService;

    @RequestMapping("/history")
    public RestResult queryHistoryTickerRate(String[] coins, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time) {
        if (coins.length > 10) {
            throw new ApplicationException(CommonErrorCode.PARAM_ERROR, "币数量不能大于10");
        }
        if (time == null) {
            time = new Date();
        }
        return RestResult.ok(coinRateService.queryHistoryCoinRate(Arrays.asList(coins), time));
    }

    @RequestMapping("/recent")
    public RestResult queryRecentlyTickerRate(String[] coins) {
        return RestResult.ok(coinRateService.queryRecentCoinRate(Arrays.asList(coins)));
    }
}
