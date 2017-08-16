/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.jubi.service.vo.TickerVo;
import com.jubi.util.ObjectMapperUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: TickerServiceTest.java, v 0.1 2017/8/16 0016 16:32 tjwang Exp $
 */
public class TickerServiceTest extends BaseServiceTest {

    @Autowired
    private TickerService tickerService;

    @Test
    public void testGetCurrentTickers() {
        List<TickerVo> ds = tickerService.getRecentTickers();
        System.out.println("size : " + ds.size());
    }

}
