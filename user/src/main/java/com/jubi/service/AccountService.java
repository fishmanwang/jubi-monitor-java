/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.jubi.dao.AccountDao;
import com.jubi.dao.entity.AccountEntity;
import com.jubi.dao.entity.AccountEntityExample;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.UserErrorCode;
import com.jubi.service.vo.AccountVo;
import com.jubi.util.BeanMapperUtil;
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

    @Autowired
    private AccountDao accountDao;

    public void createAccount(int userId) {
        Preconditions.checkArgument(userId > 0, "用户不能为空");
        AccountEntity account = new AccountEntity();
        account.setUserId(userId);
        account.setCreateTime(new Date());
        accountDao.insertSelective(account);
    }

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

}
