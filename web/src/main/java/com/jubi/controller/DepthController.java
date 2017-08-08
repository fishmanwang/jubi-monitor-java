package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.service.DepthService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/3.
 */
@RestController
@RequestMapping("/depth")
public class DepthController {

    @Autowired
    private DepthService depthService;

    @RequestMapping("/query")
    public RestResult queryCoinDepth(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date time) {
        DateTime dateTime = new DateTime(time);
        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHourOfDay();
        int minute = dateTime.getMinuteOfHour();
        return RestResult.ok(depthService.queryDepth(year, month, day, hour, minute));
    }


}
