package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/30.
 */
@RestController
@RequestMapping("/ticker")
public class TickerController {

    @Autowired
    private TickerService tickerService;

    @RequestMapping("/recent/{coin}")
    public RestResult queryRecentlyTickers(@PathVariable("coin") String coin) {
        int span = 60;
        return RestResult.ok(tickerService.queryRecentlyTickers(coin, span));
    }

}
