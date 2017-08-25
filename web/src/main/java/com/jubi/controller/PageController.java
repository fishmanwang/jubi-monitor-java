package com.jubi.controller;

import com.jubi.param.UserBean;
import com.jubi.service.AccountAdminService;
import com.jubi.service.CoinService;
import com.jubi.service.vo.AccountVo;
import com.jubi.service.vo.CoinVo;
import com.jubi.service.vo.FavoriteCoin;
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
public class PageController extends AbstractController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private AccountAdminService accountAdminService;

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
        AccountVo account = accountAdminService.getAccount(user.getId());

        ModelAndView mv = new ModelAndView();
        mv.addObject("account", account);
        mv.addObject("coins", coins);

        mv.setViewName("account_admin");
        return mv;
    }

    @RequestMapping("/notify/price")
    public ModelAndView goToNotifyPriceSettingPage() {
        ModelAndView mv = new ModelAndView();

        List<FavoriteCoin> fcoins = accountAdminService.getFavoriteCoin(getUser().getId());
        mv.addObject("fcoins", fcoins);

        mv.setViewName("price-notify-setting");
        return mv;
    }
}
