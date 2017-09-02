package com.jubi.service;

import com.jubi.dao.PriceRateMonitorSettingDao;
import com.jubi.dao.PriceRateNotifyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 价格涨幅提醒服务
 * Created by Administrator on 2017/9/2.
 */
@Service
public class PriceRateNotifyService {

    @Autowired
    private PriceRateMonitorSettingDao priceRateMonitorSettingDao;

    @Autowired
    private PriceRateNotifyDao priceRateNotifyDao;


}
