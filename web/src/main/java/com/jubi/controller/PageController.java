package com.jubi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 负责页面跳转
 * Created by Administrator on 2017/7/30.
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/ticker.html")
    public String tickerPage() {
        return "ticker";
    }

}
