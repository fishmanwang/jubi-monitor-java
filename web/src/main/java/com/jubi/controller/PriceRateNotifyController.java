package com.jubi.controller;

import com.google.common.collect.Lists;
import com.jubi.RestResult;
import com.jubi.service.PriceRateNotifyService;
import com.jubi.service.vo.PriceRateNotifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */
@RestController
@RequestMapping("/notify/rate")
public class PriceRateNotifyController extends AbstractController {

    @Autowired
    private PriceRateNotifyService priceRateNotifyService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult saveUserPriceRateNotify(@RequestBody List<PriceRateNotifyVo> vos) {
        if (vos == null) {
            vos = Lists.newArrayList();
        }
        Integer userId = getUser().getId();
        priceRateNotifyService.savePriceRateNotify(userId, vos);
        return RestResult.ok();
    }

}
