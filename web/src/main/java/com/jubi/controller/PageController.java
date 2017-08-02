package com.jubi.controller;

import com.jubi.service.CoinService;
import com.jubi.service.vo.CoinVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 负责页面跳转
 * Created by Administrator on 2017/7/30.
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private CoinService coinService;

    @RequestMapping("/recent/ticker.html")
    public ModelAndView tickerPage() {
        List<CoinVo> coins = coinService.getAllCoins();

        ModelAndView mv = new ModelAndView();
        mv.addObject("coins", coins);
        mv.setViewName("ticker-recent");

        return mv;
    }

    @RequestMapping("/history/ticker.html")
    public ModelAndView tickerHistoryPage() {
        List<CoinVo> coins = coinService.getAllCoins();

        ModelAndView mv = new ModelAndView();
        mv.addObject("coins", coins);
        mv.setViewName("ticker-history");

        return mv;
    }

    @RequestMapping("/rate.html")
    public ModelAndView ratePage() {
        List<CoinVo> coins = coinService.getAllCoins();

        ModelAndView mv = new ModelAndView();
        mv.addObject("coins", coins);
        mv.setViewName("ticker-rate");

        return mv;
    }

}
