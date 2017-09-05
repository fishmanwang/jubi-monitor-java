package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jubi.dao.PriceRateMonitorSettingDao;
import com.jubi.dao.PriceRateNotifyDao;
import com.jubi.dao.entity.*;
import com.jubi.exception.CommonErrorCode;
import com.jubi.service.vo.PriceRateNotifyVo;
import com.jubi.util.BeanMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 价格涨幅提醒服务
 * Created by Administrator on 2017/9/2.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PriceRateNotifyService {

    @Autowired
    private PriceRateMonitorSettingDao priceRateMonitorSettingDao;

    @Autowired
    private PriceRateNotifyDao priceRateNotifyDao;

    /**
     * 保存 - 先删除，后添加
     *
     * @param userId
     * @param vos
     */
    public void savePriceRateNotify(Integer userId, List<PriceRateNotifyVo> vos) {
        Preconditions.checkNotNull(userId, "用户不能为空");
        Preconditions.checkArgument(vos != null && vos.size() > 0, "配置信息不能为空");

        List<Integer> validRates = getValidRates();
        for (PriceRateNotifyVo vo : vos) {
            vo.validate();
        }

        deleteUserRateNotify(userId);
        vos = removeInvalidParams(vos);

        Date now = new Date();
        for (PriceRateNotifyVo vo : vos) {
            PriceRateNotifyEntity ent = BeanMapperUtil.map(vo, PriceRateNotifyEntity.class);
            ent.setUserId(userId);
            ent.setCreateTime(now);
            priceRateNotifyDao.insertSelective(ent);
        }
    }

    /**
     * 清楚没有配置的涨幅，主要是清除涨幅为0的默认项
     *
     * @param vos
     * @return
     */
    private List<PriceRateNotifyVo> removeInvalidParams(List<PriceRateNotifyVo> vos) {
        if (vos.size() == 0) {
            return vos;
        }
        Iterator<PriceRateNotifyVo> iter = vos.iterator();
        List<Integer> validRates = getValidRates();
        while (iter.hasNext()) {
            PriceRateNotifyVo item = iter.next();
            if (!validRates.contains(item.getRate())) {
                iter.remove();
            }
        }
        return vos;
    }

    /**
     * 查询用户价格涨幅提醒信息
     *
     * @param userId
     * @return
     */
    public List<PriceRateNotifyVo> queryUserPriceRateNotify(Integer userId) {
        Preconditions.checkNotNull(userId, "用户不能为空");

        PriceRateNotifyEntityExample exam = new PriceRateNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);

        List<PriceRateNotifyEntity> ds = priceRateNotifyDao.selectByExample(exam);

        return BeanMapperUtil.mapList(ds, PriceRateNotifyVo.class);
    }

    private void deleteUserRateNotify(Integer userId) {
        PriceRateNotifyEntityExample exam = new PriceRateNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        priceRateNotifyDao.deleteByExample(exam);
    }

    public List<Integer> getValidRates() {
        PriceRateMonitorSettingEntityExample exam = new PriceRateMonitorSettingEntityExample();
        List<PriceRateMonitorSettingEntity> ds = priceRateMonitorSettingDao.selectByExample(exam);

        List<Integer> validRates = Lists.newArrayList();
        for (PriceRateMonitorSettingEntity d : ds) {
            validRates.add(d.getRate());
        }
        return validRates;
    }

    /**
     * 删除不在保留币种的虚拟币的波动配置
     *
     * @param userId
     * @param coins  保留的币
     */
    public void deleteSetting(int userId, List<String> coins) {
        Preconditions.checkNotNull(userId);
        PriceRateNotifyEntityExample exam = new PriceRateNotifyEntityExample();
        if (coins == null) {
            exam.createCriteria().andUserIdEqualTo(userId);
        } else {
            exam.createCriteria().andUserIdEqualTo(userId).andCoinNotIn(coins);
        }
        priceRateNotifyDao.deleteByExample(exam);
    }

}
