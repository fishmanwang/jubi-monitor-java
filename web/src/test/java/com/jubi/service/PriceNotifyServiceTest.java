/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.collect.Lists;
import com.jubi.service.vo.CoinPriceNotifyVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: PriceNotifyServiceTest.java, v 0.1 2017/8/24 0024 16:10 tjwang Exp $
 */
public class PriceNotifyServiceTest extends BaseServiceTest {

    @Autowired
    private PriceNotifyService priceNotifyService;

    @Test
    public void testAddPriceNotify() {
        Integer userId = 1;

        CoinPriceNotifyVo xasVo = new CoinPriceNotifyVo();
        xasVo.setCoin("xas");
        xasVo.setPrices(Lists.newArrayList(5.5d, 6.0d));

        CoinPriceNotifyVo rssVo = new CoinPriceNotifyVo();
        rssVo.setCoin("rss");
        rssVo.setPrices(Lists.newArrayList(-2.2d, 2.7d));

        List<CoinPriceNotifyVo> vos = Lists.newArrayList();

        priceNotifyService.save(userId, vos);

    }

}
