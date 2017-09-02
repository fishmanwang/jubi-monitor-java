/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.google.common.collect.Lists;
import com.jubi.RestResult;
import com.jubi.service.PriceWaveNotifyService;
import com.jubi.service.vo.CoinPriceWaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: PriceWaveNotifyController.java, v 0.1 2017/8/31 0031 14:38 tjwang Exp $
 */
@RestController
@RequestMapping("/notify/wave")
public class PriceWaveNotifyController extends AbstractController {

    @Autowired
    private PriceWaveNotifyService priceWaveNotifyService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult savePriceWaves(@RequestBody List<CoinPriceWaveVo> params) {
        if (params == null) {
            params = Lists.newArrayList();
        }
        int userId = getUser().getId();
        priceWaveNotifyService.savePriceWaves(userId, params);
        return RestResult.ok();
    }

}
