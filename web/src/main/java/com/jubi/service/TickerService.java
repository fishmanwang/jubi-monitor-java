package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.dao.TickerExtDao;
import com.jubi.dao.entity.TickerEntity;
import com.jubi.dao.vo.TickerSpanParam;
import com.jubi.service.vo.TickerPriceVo;
import com.jubi.util.BeanMapperUtil;
import com.jubi.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
@Service
public class TickerService {

    @Autowired
    private TickerExtDao tickerExtDao;

    public List<TickerPriceVo> queryRecentlyTickers(String coin) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin));

        List<TickerPriceVo> result = Lists.newArrayList();

        Date now = new Date();
        int beginTime = DateUtils.getDayBeginTime(now);
        int end = Long.valueOf(now.getTime() / 1000).intValue();
        int span = 60; // 1分钟

        TickerSpanParam param = new TickerSpanParam();
        param.setCoin(coin);
        param.setSpan(span);
        param.setStart(beginTime);

        List<TickerEntity> ds = tickerExtDao.queryTickers(param);
        if (ds.size() == 0) {
            return result;
        }

        result = BeanMapperUtil.mapList(ds, TickerPriceVo.class);
        return result;
    }

}
