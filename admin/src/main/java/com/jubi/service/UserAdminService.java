/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.jubi.dao.*;
import com.jubi.dao.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tjwang
 * @version $Id: PriceNotifyService.java, v 0.1 2017/9/7 0007 14:06 tjwang Exp $
 */
@Service
public class UserAdminService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserCoinDao userCoinDao;

    @Autowired
    private PriceNotifyDao priceNotifyDao;

    @Autowired
    private PriceWaveNotifyDao priceWaveNotifyDao;

    @Autowired
    private PriceRateNotifyDao priceRateNotifyDao;

    @Autowired
    private EmailSendRecordDao emailSendRecordDao;

    /**
     * 删除用户相关信息
     *
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserAssociation(Integer userId) {
        Preconditions.checkNotNull(userId);

        deleteAccount(userId);
        deleteUserCoin(userId);
        deleteUserPriceNotify(userId);
        deleteUserPriceWaveNotify(userId);
        deleteUserPriceRateNotify(userId);
        deleteEmailSendRecords(userId);
    }

    /**
     * 删除账户
     *
     * @param userId
     */
    private void deleteAccount(Integer userId) {
        AccountEntityExample exam = new AccountEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        accountDao.deleteByExample(exam);
    }

    /**
     * 删除用户关注的虚拟币信息
     *
     * @param userId
     */
    private void deleteUserCoin(Integer userId) {
        UserCoinEntityExample exam = new UserCoinEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        userCoinDao.deleteByExample(exam);
    }

    /**
     * 删除价格提醒
     *
     * @param userId
     */
    private void deleteUserPriceNotify(Integer userId) {
        PriceNotifyEntityExample exam = new PriceNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        priceNotifyDao.deleteByExample(exam);
    }

    /**
     * 删除波动提醒
     *
     * @param userId
     */
    private void deleteUserPriceWaveNotify(Integer userId) {
        PriceWaveNotifyEntityExample exam = new PriceWaveNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        priceWaveNotifyDao.deleteByExample(exam);
    }

    /**
     * 删除涨幅提醒
     *
     * @param userId
     */
    private void deleteUserPriceRateNotify(Integer userId) {
        PriceRateNotifyEntityExample exam = new PriceRateNotifyEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        priceRateNotifyDao.deleteByExample(exam);
    }

    /**
     * 删除发送给用户的消息记录
     *
     * @param userId
     */
    private void deleteEmailSendRecords(Integer userId) {
        EmailSendRecordEntityExample exam = new EmailSendRecordEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        emailSendRecordDao.deleteByExample(exam);
    }
}
