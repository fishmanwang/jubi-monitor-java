/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.collect.Lists;
import com.jubi.service.vo.CoinPriceWaveVo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: PriceWaveNotifyServiceTest.java, v 0.1 2017/8/31 0031 13:52 tjwang Exp $
 */
public class PriceWaveNotifyServiceTest extends BaseServiceTest {

    @Autowired
    private PriceWaveNotifyService priceWaveNotifyService;

    @Test
    @Ignore
    public void testSavePriceWaves() {
        int userId = 1;

        CoinPriceWaveVo xas = new CoinPriceWaveVo();
        xas.setCoin("xas");
        xas.setSpan(10);
        xas.setRate(2d);

        CoinPriceWaveVo tic = new CoinPriceWaveVo();
        tic.setCoin("tic");
        tic.setSpan(15);
        tic.setRate(2.5d);

        CoinPriceWaveVo rss = new CoinPriceWaveVo();
        rss.setCoin("rss");
        rss.setSpan(20);
        rss.setRate(2d);

        List<CoinPriceWaveVo> params = Lists.newArrayList(xas, rss, tic);

        priceWaveNotifyService.savePriceWaves(userId, params);
    }

    @Test
    @Ignore
    public void testDeleteSetting() {
        int userId = 1;
        List<String> coins = Lists.newArrayList("xas", "rss");
        priceWaveNotifyService.deleteSetting(userId, coins);
    }

}
