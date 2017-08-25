package com.jubi.controller;

import com.google.common.collect.Lists;
import com.jubi.RestResult;
import com.jubi.controller.vo.TickerShowVo;
import com.jubi.service.CoinService;
import com.jubi.service.TickerService;
import com.jubi.service.vo.CoinVo;
import com.jubi.service.vo.TickerVo;
import com.jubi.util.BeanMapperUtil;
import com.jubi.util.ObjectMapperUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/7/30.
 */
@RestController
@RequestMapping("/ticker")
public class TickerController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private TickerService tickerService;

    @RequestMapping("/{coin}")
    public RestResult queryTickers(@PathVariable("coin") String coin, Integer span) {
        return RestResult.ok(tickerService.queryTickers(coin, span));
    }

    @RequestMapping(value = "/coins/recent", method = RequestMethod.GET)
    public RestResult queryCoinsRecentTickers() {
        List<TickerVo> tickers = tickerService.getRecentTickers();
        System.out.println(ObjectMapperUtil.writePretty(tickers));

        if (tickers.size() == 0) {
            return RestResult.ok();
        }

        Map<String, TickerVo> tickerMap = tickers.stream().collect(Collectors.toMap(TickerVo::getCoin, p -> p));

        DateTime d = new DateTime();
        int beginPk = Long.valueOf(DateUtils.truncate(d.toDate(), Calendar.DAY_OF_MONTH).getTime() / 1000).intValue();
        d = d.plusDays(-3);
        int threePk = Long.valueOf(DateUtils.truncate(d.toDate(), Calendar.DAY_OF_MONTH).getTime() / 1000).intValue();
        d = d.plusDays(-4);
        int sevenPk = Long.valueOf(DateUtils.truncate(d.toDate(), Calendar.DAY_OF_MONTH).getTime() / 1000).intValue();
        d = d.plusDays(-8);
        int fiftyPk = Long.valueOf(DateUtils.truncate(d.toDate(), Calendar.DAY_OF_MONTH).getTime() / 1000).intValue();
        d = d.plusDays(-15);
        int monthPk = Long.valueOf(DateUtils.truncate(d.toDate(), Calendar.DAY_OF_MONTH).getTime() / 1000).intValue();


        List<TickerShowVo> rs = Lists.newArrayList();
        List<CoinVo> vos = coinService.getAllCoins();
        for (CoinVo vo : vos) {
            String coin = vo.getCode();
            String name = vo.getName();
            TickerVo ticker = tickerMap.get(coin);
            TickerShowVo svo = BeanMapperUtil.map(ticker, TickerShowVo.class);
            svo.setName(name);
            Double beginPrice = tickerService.queryPriceAtPk(coin, beginPk);
            Double threePrice = tickerService.queryPriceAtPk(coin, threePk);
            Double sevenPrice = tickerService.queryPriceAtPk(coin, sevenPk);
            Double fiftyPrice = tickerService.queryPriceAtPk(coin, fiftyPk);
            Double monthPrice = tickerService.queryPriceAtPk(coin, monthPk);
            svo.setBeginPrice(beginPrice);
            svo.setThreePrice(threePrice);
            svo.setSevenPrice(sevenPrice);
            svo.setFiftyPrice(fiftyPrice);
            svo.setMonthPrice(monthPrice);
            rs.add(svo);
        }

        return RestResult.ok(rs);
    }

    @RequestMapping("/recent/{coin}")
    public RestResult queryRecentlyTickers(@PathVariable("coin") String coin) {
        return RestResult.ok(tickerService.queryRecentlyTickers(coin));
    }

    @RequestMapping("/history/{coin}")
    public RestResult queryHistoryTickers(@PathVariable("coin") String coin, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time) {
        DateTime dateTime = new DateTime(time);
        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();
        return RestResult.ok(tickerService.queryHistoryTickers(coin, year, month, day));
    }

}
