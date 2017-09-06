package com.jubi.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jubi.controller.vo.CoinPriceNotifyViewVo;
import com.jubi.controller.vo.CoinPriceWaveView;
import com.jubi.controller.vo.PriceRateNotifyView;
import com.jubi.param.UserBean;
import com.jubi.service.*;
import com.jubi.service.vo.*;
import com.jubi.util.BeanMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 负责页面跳转
 * Created by Administrator on 2017/7/30.
 */
@Controller
@RequestMapping("/page")
public class PageController extends AbstractController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private TickerService tickerService;

    @Autowired
    private AccountFrontService accountFrontService;

    @Autowired
    private PriceWaveNotifyService priceWaveNotifyService;

    @Autowired
    private PriceRateNotifyService priceRateNotifyService;

    @Autowired
    private EmailSendRecordService emailSendRecordService;

    @RequestMapping("/index.html")
    public ModelAndView index() {
        List<CoinVo> coins = coinService.getAllCoins();
        ModelAndView mv = new ModelAndView();
        mv.addObject("coins", coins);
        mv.setViewName("index");

        return mv;
    }

    @RequestMapping("login.html")
    public String login() {
        return "login";
    }

    @RequestMapping("/ticker.html")
    public ModelAndView tickerPage() {
        List<CoinVo> coins = coinService.getAllCoins();

        ModelAndView mv = new ModelAndView();
        mv.addObject("coins", coins);
        mv.setViewName("ticker");

        return mv;
    }

    @RequestMapping("/recent/ticker.html")
    public ModelAndView tickerRecnetPage() {
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

    @RequestMapping("/recent/rate.html")
    public ModelAndView rateRecentPage() {
        List<CoinVo> coins = coinService.getAllCoins();

        ModelAndView mv = new ModelAndView();
        mv.addObject("coins", coins);
        mv.setViewName("ticker-recent-rate");

        return mv;
    }

    @RequestMapping("/history/rate.html")
    public ModelAndView rateHistoryPage() {
        List<CoinVo> coins = coinService.getAllCoins();

        ModelAndView mv = new ModelAndView();
        mv.addObject("coins", coins);
        mv.setViewName("ticker-history-rate");

        return mv;
    }

    @RequestMapping("/coin/depth/cmp.html")
    public String coinDepthCmpPage() {
        return "coin-depth-cmp";
    }

    @RequestMapping("/coin/depth/history.html")
    public ModelAndView coinDepthHistoryPage() {
        List<CoinVo> coins = coinService.getAllCoins();

        ModelAndView mv = new ModelAndView();
        mv.addObject("coins", coins);
        mv.setViewName("coin-depth-history");

        return mv;
    }

    @RequestMapping("/coin/info.html")
    public ModelAndView coinOrderInfoPage() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("coin-info");

        return mv;
    }

    @RequestMapping("/account/admin.html")
    public ModelAndView goToAccountAdmin() {
        UserBean user = (UserBean) getUser();
        List<CoinVo> coins = coinService.getAllCoins();
        AccountVo account = accountFrontService.getAccount(user.getId());

        ModelAndView mv = new ModelAndView();
        mv.addObject("account", account);
        mv.addObject("coins", coins);

        mv.setViewName("account_admin");
        return mv;
    }

    @RequestMapping("/notify/price")
    public ModelAndView goToNotifyPriceSettingPage() {
        ModelAndView mv = new ModelAndView();

        List<CoinPriceNotifyViewVo> ds = Lists.newArrayList();

        List<TickerVo> tickers = tickerService.getRecentTickers();
        Map<String, TickerVo> tickerMap = tickers.stream().collect(Collectors.toMap(TickerVo::getCoin, p -> p));

        Map<String, String> coinMap = coinService.getAllCoinsMap();
        List<FavoriteCoin> fcoins = accountFrontService.getFavoriteCoin(getUser().getId());
        for (FavoriteCoin fc : fcoins) {
            String coin = fc.getCoin();
            String name = coinMap.get(coin);
            CoinPriceNotifyViewVo vo = BeanMapperUtil.map(fc, CoinPriceNotifyViewVo.class);
            vo.setName(name);
            TickerVo ticker = tickerMap.get(coin);
            vo.setPrice(ticker.getLast());
            ds.add(vo);
        }

        mv.addObject("items", ds);

        mv.setViewName("price-notify-setting");
        return mv;
    }

    @RequestMapping("/notify/wave")
    public ModelAndView goToNotifyPriceWaveSettingPage() {
        ModelAndView mv = new ModelAndView();

        List<CoinPriceWaveView> ds = Lists.newArrayList();

        int userId = getUser().getId();
        Map<String, String> coinMap = coinService.getAllCoinsMap();
        List<FavoriteCoin> fcoins = accountFrontService.getFavoriteCoin(userId);
        Set<String> fcs = getFavoriateCoins(userId);

        List<CoinPriceWaveVo> vos = priceWaveNotifyService.getUserPriceWaveSettings(userId);
        for (CoinPriceWaveVo vo : vos) {
            CoinPriceWaveView view = BeanMapperUtil.map(vo, CoinPriceWaveView.class);
            String coin = vo.getCoin();
            String name = coinMap.get(coin);
            view.setName(name);
            view.setSetted(true);
            ds.add(view);
            fcs.remove(coin);
        }
        for (String coin : fcs) {
            CoinPriceWaveView view = new CoinPriceWaveView();
            view.setCoin(coin);
            String name = coinMap.get(coin);
            view.setName(name);
            view.setSpan(30);
            view.setRate(5d);
            ds.add(view);
        }

        mv.addObject("items", ds);
        mv.setViewName("price-wave-notify-setting");

        return mv;
    }

    private Set<String> getFavoriateCoins(Integer userId) {
        Set<String> fcs = Sets.newHashSet();

        List<FavoriteCoin> fcoins = accountFrontService.getFavoriteCoin(userId);
        for (FavoriteCoin fc : fcoins) {
            String coin = fc.getCoin();
            fcs.add(coin);
        }
        return fcs;
    }

    @RequestMapping("/notify/rate")
    public ModelAndView goToNotifyPricerRateSettingPage() {
        ModelAndView mv = new ModelAndView();

        List<PriceRateNotifyView> ds = Lists.newArrayList();

        Integer userId = getUser().getId();
        Map<String, String> coinMap = coinService.getAllCoinsMap();
        List<PriceRateNotifyVo> vos = priceRateNotifyService.queryUserPriceRateNotify(userId);
        Set<String> fcs = getFavoriateCoins(userId);

        for (PriceRateNotifyVo vo : vos) {
            PriceRateNotifyView view = BeanMapperUtil.map(vo, PriceRateNotifyView.class);
            String coin = view.getCoin();
            view.setName(coinMap.get(coin));
            ds.add(view);
            fcs.remove(coin);
        }
        for (String coin : fcs) {
            PriceRateNotifyView view = new PriceRateNotifyView();
            view.setCoin(coin);
            view.setName(coinMap.get(coin));
            view.setRate(0);
            ds.add(view);
        }

        List<Integer> validRates = priceRateNotifyService.getValidRates();
        Collections.sort(validRates);

        mv.addObject("items", ds);
        mv.addObject("validRates", validRates);
        mv.setViewName("price-rate-notify-setting");

        return mv;
    }

    @RequestMapping("/email/record")
    public ModelAndView goToEmailSendRecord() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("email-send-record");
        return mv;
    }
}
