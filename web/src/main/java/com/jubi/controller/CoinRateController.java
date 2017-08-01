/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.service.CoinRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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

    @RequestMapping("/recent")
    public RestResult queryRecentlyTickerRate(String[] coins, Integer span) {
        if (span == null) {
            span = 300;
        }
        return RestResult.ok(coinRateService.queryCoinRate(Arrays.asList(coins), span));
    }
}
