package com.jubi.controller;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.jubi.RestResult;
import com.jubi.context.SessionContext;
import com.jubi.exception.CommonErrorCode;
import com.jubi.service.CoinDepthService;
import com.jubi.service.vo.DepthRealVo;
import com.jubi.service.vo.DepthVo;
import com.mybatis.domain.PageBounds;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
@RestController
@RequestMapping("/depth")
public class CoinDepthController extends AbstractController {

    @Autowired
    private CoinDepthService depthService;

    @RequestMapping("/real/{coin}")
    public RestResult queryCoinRealTimeDepth(@PathVariable("coin") String coin) {
        Optional<DepthRealVo> opt = depthService.queryRealTimeDepth(coin);
        if (opt.isPresent()) {
            return RestResult.ok(opt.get());
        }
        return RestResult.fail(CommonErrorCode.BIZ_ERROR.getStatus(), "获取实时信息失败，请重试。");
    }

    @RequestMapping("/query")
    public RestResult queryDepth(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date time) {
        DateTime dateTime = new DateTime(time);
        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHourOfDay();
        int minute = dateTime.getMinuteOfHour();
        return RestResult.ok(depthService.queryDepth(year, month, day, hour, minute));
    }

    @RequestMapping("/{coin}")
    public RestResult queryCoinDepth(@PathVariable("coin") String coin,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date time) {
        Preconditions.checkNotNull(time);
        PageBounds pb = getPageBounds(SessionContext.getRequest());
        List<DepthVo> ds = depthService.queryCoinDepth(coin, time);
        return RestResult.ok(ds);
    }


}
