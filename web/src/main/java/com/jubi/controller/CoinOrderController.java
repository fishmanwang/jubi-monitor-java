/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.service.CoinOrderService;
import com.jubi.service.vo.CoinOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    @RequestMapping(value = "{coin}", method = RequestMethod.GET)
    public RestResult queryCoinOrders(@PathVariable("coin") String coin,
                                      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date time) {
        List<CoinOrderVo> ds = coinOrderService.queryOrders(coin, time);
        return RestResult.ok(ds);
    }

}
