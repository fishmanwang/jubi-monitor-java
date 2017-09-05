package com.jubi.listener;

import com.google.common.collect.Lists;
import com.jubi.event.FavoriteCoinChangeEvent;
import com.jubi.event.param.FavoriteCoinChangeSource;
import com.jubi.service.PriceNotifyService;
import com.jubi.service.PriceRateNotifyService;
import com.jubi.service.PriceWaveNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */
@Component
public class FavoriteCoinChangeEventListener implements ApplicationListener<FavoriteCoinChangeEvent> {

    @Autowired
    private PriceNotifyService priceNotifyService;

    @Autowired
    private PriceWaveNotifyService priceWaveNotifyService;

    @Autowired
    private PriceRateNotifyService priceRateNotifyService;

    @Override
    public void onApplicationEvent(FavoriteCoinChangeEvent event) {
        FavoriteCoinChangeSource source = (FavoriteCoinChangeSource) event.getSource();
        int userId = source.getUserId();
        List<String> coins = source.getCoins();
        if (coins == null) {
            coins = Lists.newArrayList();
        }
        priceNotifyService.deleteUnfavoriteCoins(userId, coins);
        priceWaveNotifyService.deleteSetting(userId, coins);
        priceRateNotifyService.deleteSetting(userId, coins);
    }

}
