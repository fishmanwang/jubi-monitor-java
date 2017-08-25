/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.service.PriceNotifyService;
import com.jubi.service.vo.CoinPriceNotifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: PriceNotifyController.java, v 0.1 2017/8/25 0025 11:00 tjwang Exp $
 */
@RestController
@RequestMapping("/notify/price")
public class PriceNotifyController extends AbstractController {

    @Autowired
    private PriceNotifyService priceNotifyService;

    @RequestMapping("/user")
    public RestResult getUserPriceNotifies() {
        Integer userId = getUser().getId();
        List<CoinPriceNotifyVo> ds = priceNotifyService.getUserPriceNotifies(userId);
        return RestResult.ok(ds);
    }

}
