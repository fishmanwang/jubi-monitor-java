/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.jubi.service.vo.DepthVo;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: DepthServiceTest.java, v 0.1 2017/8/7 0007 10:23 tjwang Exp $
 */
public class DepthServiceTest extends BaseServiceTest {

    @Autowired
    private DepthService depthService;

    @Test
    public void testQueryCurrentDepth() {
        DateTime dateTime = new DateTime();
        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHourOfDay();
        int minute = dateTime.getMinuteOfHour();
        List<DepthVo> ds = depthService.queryDepth(year, month, day, hour, minute);
        assertTrue(ds.size() > 0);
    }

}
