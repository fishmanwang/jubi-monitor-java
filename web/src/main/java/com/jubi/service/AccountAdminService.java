/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jubi.dao.AccountDao;
import com.jubi.dao.UserCoinDao;
import com.jubi.dao.entity.AccountEntity;
import com.jubi.dao.entity.AccountEntityExample;
import com.jubi.dao.entity.UserCoinEntity;
import com.jubi.dao.entity.UserCoinEntityExample;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.UserErrorCode;
import com.jubi.service.vo.AccountVo;
import com.jubi.service.vo.FavoriteCoin;
import com.jubi.util.BeanMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    private AccountDao accountDao;

    @Autowired
    private UserCoinDao userCoinDao;

    public void saveAccount(int userId, AccountVo vo) {
        Preconditions.checkArgument(userId > 0, "用户不能为空");
        AccountEntityExample exam = new AccountEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);

        AccountEntity account = BeanMapperUtil.map(vo, AccountEntity.class);
        account.setUpdateTime(new Date());
        accountDao.updateByExampleSelective(account, exam);
    }

    public AccountVo getAccount(int userId) {
        Preconditions.checkArgument(userId > 0, "用户不能为空");

        AccountEntityExample exam = new AccountEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);

        List<AccountEntity> ds = accountDao.selectByExample(exam);
        if (ds.size() == 0) {
            throw new ApplicationException(UserErrorCode.ACCOUNT_NOT_EXISTS);
        }
        return BeanMapperUtil.map(ds.get(0), AccountVo.class);
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
        cleanCurrentFavoriteCoins(userId);
        for (FavoriteCoin fc : fcs) {
            UserCoinEntity ent = new UserCoinEntity();
            ent.setUserId(userId);
            ent.setCoin(fc.getCoin());
            ent.setPriority(fc.getPriority());
            userCoinDao.insertSelective(ent);
        }
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
