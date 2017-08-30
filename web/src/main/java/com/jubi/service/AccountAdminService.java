/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.dao.UserCoinDao;
import com.jubi.dao.entity.UserCoinEntity;
import com.jubi.dao.entity.UserCoinEntityExample;
import com.jubi.event.FavoriteCoinChangeEvent;
import com.jubi.event.param.FavoriteCoinChangeSource;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.CommonErrorCode;
import com.jubi.service.vo.AccountVo;
import com.jubi.service.vo.FavoriteCoin;
import com.jubi.spring.SpringContextHolder;
import com.jubi.util.BeanMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账户信息管理服务
 *
 * @author tjwang
 * @version $Id: AccountService.java, v 0.1 2017/8/17 0017 17:19 tjwang Exp $
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountAdminService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserCoinDao userCoinDao;

    public void saveAccount(int userId, AccountVo vo) {
        Preconditions.checkArgument(userId > 0, "用户不能为空");
        accountService.saveAccount(userId, vo);
    }

    public AccountVo getAccount(int userId) {
        Preconditions.checkArgument(userId > 0, "用户不能为空");
        return accountService.getAccount(userId);
    }

    /**
     * 设置感兴趣的币
     *
     * @param userId
     * @param fcs
     */
    public void setFavoriteCoins(Integer userId, List<FavoriteCoin> fcs) {
        Preconditions.checkNotNull(userId, "用户不能为空");
        if (fcs == null) {
            fcs = Lists.newArrayList();
        }
        if (fcs.size() > 10) {
            throw new ApplicationException(CommonErrorCode.PARAM_ERROR, "最多关注10个虚拟币");
        }
        cleanCurrentFavoriteCoins(userId);
        List<String> coins = Lists.newArrayList();
        for (FavoriteCoin fc : fcs) {
            coins.add(fc.getCoin());
            UserCoinEntity ent = new UserCoinEntity();
            ent.setUserId(userId);
            ent.setCoin(fc.getCoin());
            ent.setPriority(fc.getPriority());
            userCoinDao.insertSelective(ent);
        }
        FavoriteCoinChangeSource source = new FavoriteCoinChangeSource();
        source.setUserId(userId);
        source.setCoins(coins);
        SpringContextHolder.getApplicationContext().publishEvent(new FavoriteCoinChangeEvent(source));
    }

    /**
     * 获取用户感兴趣的币
     *
     * @param userId
     * @return
     */
    public List<FavoriteCoin> getFavoriteCoin(Integer userId) {
        Preconditions.checkNotNull(userId, "用户不能为空");

        UserCoinEntityExample exam = new UserCoinEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);

        List<UserCoinEntity> ds = userCoinDao.selectByExample(exam);

        return BeanMapperUtil.mapList(ds, FavoriteCoin.class);
    }

    /**
     * 清除已有的感兴趣的币
     *
     * @param userId
     */
    private void cleanCurrentFavoriteCoins(Integer userId) {
        UserCoinEntityExample exam = new UserCoinEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);

        userCoinDao.deleteByExample(exam);
    }

}
