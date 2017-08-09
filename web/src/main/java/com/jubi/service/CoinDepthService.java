/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.jubi.dao.CoinDepthDao;
import com.jubi.dao.CoinDepthExtDao;
import com.jubi.dao.entity.CoinDepthExample;
import com.jubi.dao.entity.CoinDepthWithBLOBs;
import com.jubi.service.vo.DepthRealVo;
import com.jubi.service.vo.DepthVo;
import com.jubi.util.BeanMapperUtil;
import com.jubi.util.HttpClientUtil;
import com.jubi.util.ObjectMapperUtil;
import com.mybatis.domain.PageBounds;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 市场深度服务
 *
 * @author tjwang
 * @version $Id: DepthService.java, v 0.1 2017/8/7 0007 9:57 tjwang Exp $
 */
@Service
public class CoinDepthService {

    @Autowired
    private CoinDepthDao depthDao;

    @Autowired
    private CoinDepthExtDao coinDepthExtDao;

    public Optional<DepthRealVo> queryRealTimeDepth(String coin) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");
        Optional<Double> priceOpt = getRealTicker(coin);
        if (!priceOpt.isPresent()) {
            return Optional.absent();
        }
        Optional<DepthRealVo> voOpt = getRealDepth(coin);
        if (!voOpt.isPresent()) {
            return Optional.absent();
        }
        DepthRealVo vo = voOpt.get();
        vo.setCoin(coin);
        vo.setPrice(priceOpt.get());
        return Optional.of(vo);
    }

    private Optional<Double> getRealTicker(String coin) {
        String resp = HttpClientUtil.get("https://www.jubi.com/api/v1/ticker/?coin=" + coin + "&t=" + Math.random());
        if (StringUtils.isBlank(resp)) {
            return Optional.absent();
        }
        return Optional.of(Double.valueOf((String)ObjectMapperUtil.read(resp, LinkedHashMap.class).get("last")));
    }

    private Optional<DepthRealVo> getRealDepth(String coin) {
        String resp = HttpClientUtil.get("https://www.jubi.com/api/v1/depth/?coin=" + coin + "&t=" + Math.random());
        if (StringUtils.isBlank(resp)) {
            return Optional.absent();
        }
        return Optional.of(ObjectMapperUtil.read(resp, DepthRealVo.class));
    }

    public List<DepthVo> queryDepth(int year, int month, int day, int hour, int minute) {
        DateTime dateTime = new DateTime(year, month, day, hour, minute, 0);
        int time = Long.valueOf(dateTime.getMillis() / 1000).intValue();
        List<CoinDepthWithBLOBs> ds = coinDepthExtDao.queryDepth(time);
        return BeanMapperUtil.mapList(ds, DepthVo.class);
    }

    /**
     * 查询历史深度
     * @param coin
     * @param begin
     * @return
     */
    public List<DepthVo> queryCoinDepth(String coin, Date begin) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin), "币不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(coin));
        Preconditions.checkNotNull(begin, "开始时间不能为空");

        int beginTime = Long.valueOf(begin.getTime() / 1000).intValue();

        CoinDepthExample exam = new CoinDepthExample();
        exam.createCriteria().andCoinEqualTo(coin).andPkGreaterThanOrEqualTo(beginTime);

        PageBounds pb = new PageBounds(1, 100, false);
        List<CoinDepthWithBLOBs> ds = depthDao.selectByExampleWithBLOBsWithPageBounds(exam, pb);
        return BeanMapperUtil.mapList(ds, DepthVo.class);
    }

}
