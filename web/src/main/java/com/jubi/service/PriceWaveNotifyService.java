/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.dao.PriceWaveNotifyDao;
import com.jubi.dao.entity.PriceWaveNotifyEntity;
import com.jubi.dao.entity.PriceWaveNotifyEntityExample;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.CommonErrorCode;
import com.jubi.service.vo.CoinPriceWaveVo;
import com.jubi.util.BeanMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 价格波动提醒服务
 *
 * @author tjwang
 * @version $Id: PriceWaveNotifyService.java, v 0.1 2017/8/31 0031 13:19 tjwang Exp $
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PriceWaveNotifyService {

    @Autowired
    private PriceWaveNotifyDao priceWaveNotifyDao;

    /**
     * 获取用户配置的提醒信息
     *
     * @param userId
     * @return
     */
    public List<CoinPriceWaveVo> getUserPriceWaveSettings(int userId) {
        Preconditions.checkNotNull(userId);
        PriceWaveNotifyEntityExample exam = new PriceWaveNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        List<PriceWaveNotifyEntity> ds = priceWaveNotifyDao.selectByExample(exam);
        return BeanMapperUtil.mapList(ds, CoinPriceWaveVo.class);
    }

    /**
     * 保存价格波动设置
     *
     * @param userId
     * @param params
     */
    public void savePriceWaves(int userId, List<CoinPriceWaveVo> params) {
        Preconditions.checkNotNull(userId);
        if (params == null) {
            params = Lists.newArrayList();
        }

        if (params.size() > 5) {
            throw new ApplicationException(CommonErrorCode.PARAM_ERROR, "波动监控不能超过5个虚拟币");
        }

        for (CoinPriceWaveVo param : params) {
            param.validate();
        }

        PriceWaveNotifyEntityExample exam = new PriceWaveNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        priceWaveNotifyDao.deleteByExample(exam);

        for (CoinPriceWaveVo param : params) {
            PriceWaveNotifyEntity ent = BeanMapperUtil.map(param, PriceWaveNotifyEntity.class);
            ent.setUserId(userId);
            ent.setCreateTime(new Date());
            priceWaveNotifyDao.insertSelective(ent);
        }
    }

    /**
     * 删除不在保留币种的虚拟币的波动配置
     *
     * @param userId
     * @param coins  保留的币
     */
    public void deleteSetting(int userId, List<String> coins) {
        Preconditions.checkNotNull(userId);
        PriceWaveNotifyEntityExample exam = new PriceWaveNotifyEntityExample();
        if (coins == null) {
            exam.createCriteria().andUserIdEqualTo(userId);
        } else {
            exam.createCriteria().andUserIdEqualTo(userId).andCoinNotIn(coins);
        }
        priceWaveNotifyDao.deleteByExample(exam);
    }
}
