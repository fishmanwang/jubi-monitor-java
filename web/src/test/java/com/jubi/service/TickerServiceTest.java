/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.jubi.util.ObjectMapperUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author tjwang
 * @version $Id: TickerServiceTest.java, v 0.1 2017/8/16 0016 16:32 tjwang Exp $
 */
public class TickerServiceTest extends BaseServiceTest {

    @Autowired
    private TickerService tickerService;

    public static void main(String[] args) {
        System.out.println(ObjectMapperUtil.write(Arrays.asList("aa", "bb", "cc")));
        System.out.println(ObjectMapperUtil.read(ObjectMapperUtil.write(Arrays.asList("aa", "bb", "cc")), List.class));
    }

    @Test
    public void testGetCurrentTickers() {
        tickerService.getRecentTickers();
    }

}
