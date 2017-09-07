package com.jubi.dao;

import com.jubi.dao.entity.AccountEntity;
import com.jubi.dao.entity.AccountEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountDao {
    int deleteByExample(AccountEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountEntity record);

    int insertSelective(AccountEntity record);

    List<AccountEntity> selectByExampleWithPageBounds(AccountEntityExample example, PageBounds pageBounds);

    List<AccountEntity> selectByExample(AccountEntityExample example);

    AccountEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountEntity record, @Param("example") AccountEntityExample example);

    int updateByExample(@Param("record") AccountEntity record, @Param("example") AccountEntityExample example);

    int updateByPrimaryKeySelective(AccountEntity record);

    int updateByPrimaryKey(AccountEntity record);
}