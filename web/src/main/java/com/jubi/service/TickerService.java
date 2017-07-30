package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.dao.TickerDao;
import com.jubi.service.vo.TickerPriceVo;
import com.jubi.util.TickerUtil;
import com.jubi.entity.TickerEntity;
import com.mybatis.domain.PageBounds;
import com.mybatis.domain.SortBy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
@Service
public class TickerService {

    @Autowired
    private TickerDao tickerDao;

    public List<TickerPriceVo> queryRecentlyTickers(String coin, int span) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin));

        if (span == 0) {
            span = 600; // 十分钟
        }

        List<TickerPriceVo> result = Lists.newArrayList();

        String tableName = TickerUtil.getTickerTableNameByCoin(coin);
        PageBounds pb = new PageBounds(1, 2000);
        SortBy sy = SortBy.create("pk", SortBy.Direction.DESC.toString());
        pb.setOrders(Arrays.asList(sy));

        List<TickerEntity> ds = tickerDao.queryTickers(tableName, span, pb);
        if (ds.size() == 0) {
            return result;
        }

        ds.forEach(p -> {
            TickerPriceVo vo = new TickerPriceVo();
            vo.setPk(p.getPk());
            vo.setPrice(p.getLast());
            result.add(vo);
        });
        return result;
    }



}
