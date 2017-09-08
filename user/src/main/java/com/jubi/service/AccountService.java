/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.jubi.dao.AccountDao;
import com.jubi.dao.UserGradeDao;
import com.jubi.dao.entity.AccountEntity;
import com.jubi.dao.entity.AccountEntityExample;
import com.jubi.dao.entity.UserGrade;
import com.jubi.dao.entity.UserGradeExample;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.UserErrorCode;
import com.jubi.service.vo.AccountVo;
import com.jubi.service.vo.UserGradeVo;
import com.jubi.util.BeanMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tjwang
 * @version $Id: AccountService.java, v 0.1 2017/8/22 0022 15:09 tjwang Exp $
 */
@Service
public class AccountService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserGradeDao userGradeDao;

    public void createAccount(int userId) {
        Preconditions.checkNotNull(userId, "用户不能为空");
        AccountEntity account = new AccountEntity();
        account.setUserId(userId);
        account.setGrade(1); // 新注册用户，默认级别1
        account.setCreateTime(new Date());
        accountDao.insertSelective(account);
    }

    public void saveAccount(int userId, AccountVo vo) {
        Preconditions.checkNotNull(userId, "用户不能为空");
        AccountEntityExample exam = new AccountEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);

        AccountEntity account = BeanMapperUtil.map(vo, AccountEntity.class);
        account.setUpdateTime(new Date());
        accountDao.updateByExampleSelective(account, exam);
    }

    public AccountVo getAccount(int userId) {
        Preconditions.checkNotNull(userId, "用户不能为空");

        AccountEntity d = doGetAccount(userId);
        return BeanMapperUtil.map(d, AccountVo.class);
    }

    public AccountEntity doGetAccount(int userId) {
        AccountEntityExample exam = new AccountEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);

        List<AccountEntity> ds = accountDao.selectByExample(exam);
        if (ds.size() == 0) {
            throw new ApplicationException(UserErrorCode.ACCOUNT_NOT_EXISTS);
        }
        return ds.get(0);
    }

    /**
     * 获取用户登记信息
     *
     * @param userId
     * @return
     */
    public UserGradeVo getUserGrade(Integer userId) {
        Preconditions.checkNotNull(userId, "用户不能为空");
        AccountEntity account = doGetAccount(userId);
        Integer grade = account.getGrade();

        UserGradeExample exam = new UserGradeExample();
        exam.createCriteria().andGradeEqualTo(grade);
        List<UserGrade> ds = userGradeDao.selectByExample(exam);
        if (ds.size() == 0) {
            logger.info("{} 用户的等级 {}, 不存在。", userId, grade);
            throw new ApplicationException(UserErrorCode.USER_GRADE_NOT_EXISTS);
        }
        UserGrade d = ds.get(0);

        UserGradeVo vo = new UserGradeVo();
        vo.setGrade(grade);
        vo.setEmailCount(vo.getEmailCount());
        return vo;
    }
}
