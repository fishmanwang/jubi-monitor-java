/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jubi.dao.PriceNotifyDao;
import com.jubi.dao.entity.PriceNotifyEntity;
import com.jubi.dao.entity.PriceNotifyEntityExample;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.CommonErrorCode;
import com.jubi.service.vo.CoinPriceNotifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 价格通知服务
 *
 * @author tjwang
 * @version $Id: PriceNotifyService.java, v 0.1 2017/8/24 0024 15:16 tjwang Exp $
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PriceNotifyService {

    @Autowired
    private PriceNotifyDao priceNotifyDao;

    /**
     * 获取用户价格提示配置
     *
     * @param userId
     */
    public List<CoinPriceNotifyVo> getUserPriceNotifies(Integer userId) {
        List<CoinPriceNotifyVo> rs = Lists.newArrayList();
        PriceNotifyEntityExample exam = new PriceNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        List<PriceNotifyEntity> ds = priceNotifyDao.selectByExample(exam);
        if (ds.size() == 0) {
            return rs;
        }
        Map<String, CoinPriceNotifyVo> map = Maps.newHashMap();
        for (PriceNotifyEntity d : ds) {
            String coin = d.getCoin();
            CoinPriceNotifyVo vo = map.get(coin);
            if (vo == null) {
                vo = new CoinPriceNotifyVo();
                vo.setCoin(coin);
                map.putIfAbsent(coin, vo);
            }
            vo.getPrices().add(d.getPrice());
        }
        rs.addAll(map.values());
        return rs;
    }

    /**
     * 增加价格提示配置
     *
     * @param userId
     * @param vos
     */
    public void save(Integer userId, List<CoinPriceNotifyVo> vos) {
        Preconditions.checkNotNull(userId);
        Preconditions.checkNotNull(vos);

        if (vos.size() == 0) {
            return;
        }
        validCoinPriceNotifyVos(vos);
        List<PriceNotifyEntity> ents = Lists.newArrayList();
        for (CoinPriceNotifyVo vo : vos) {
            ents.addAll(build(userId, vo));
        }
        deleteUserPriceNotifies(userId);
        for (PriceNotifyEntity ent : ents) {
            priceNotifyDao.insertSelective(ent);
        }
    }

    private void validCoinPriceNotifyVos(List<CoinPriceNotifyVo> vos) {
        for (CoinPriceNotifyVo vo : vos) {
            if (vo.getPrices().size() > 3) {
                String msg = vo.getCoin().toUpperCase()+" 价格设置超过了3个";
                throw new ApplicationException(CommonErrorCode.PARAM_ERROR, msg);
            }
        }
    }

    private void deleteUserPriceNotifies(Integer userId) {
        PriceNotifyEntityExample exam = new PriceNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        priceNotifyDao.deleteByExample(exam);
    }

    private List<PriceNotifyEntity> build(Integer userId, CoinPriceNotifyVo vo) {
        List<PriceNotifyEntity> rs = Lists.newArrayList();
        String coin = vo.getCoin();
        List<Double> prices = vo.getPrices();
        Date now = new Date();
        for (Double price : prices) {
            PriceNotifyEntity ent = new PriceNotifyEntity();
            ent.setUserId(userId);
            ent.setCoin(coin);
            ent.setPrice(price);
            ent.setCreateTime(now);
            rs.add(ent);
        }
        return rs;
    }

    /**
     * 删除掉不再关注的虚拟币的价格提醒
     *
     * @param coins：依然关注的虚拟币
     */
    public void deleteUnfavoriteCoins(int userId, List<String> coins) {
        Preconditions.checkNotNull(userId);
        Preconditions.checkNotNull(coins);

        PriceNotifyEntityExample exam = new PriceNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId).andCoinNotIn(coins);
        priceNotifyDao.deleteByExample(exam);
    }

}
