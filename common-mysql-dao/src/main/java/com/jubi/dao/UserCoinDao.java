package com.jubi.dao;

import com.jubi.dao.entity.UserCoinEntity;
import com.jubi.dao.entity.UserCoinEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCoinDao {
    int deleteByExample(UserCoinEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCoinEntity record);

    int insertSelective(UserCoinEntity record);

    List<UserCoinEntity> selectByExampleWithPageBounds(UserCoinEntityExample example, PageBounds pageBounds);

    List<UserCoinEntity> selectByExample(UserCoinEntityExample example);

    UserCoinEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCoinEntity record, @Param("example") UserCoinEntityExample example);

    int updateByExample(@Param("record") UserCoinEntity record, @Param("example") UserCoinEntityExample example);

    int updateByPrimaryKeySelective(UserCoinEntity record);

    int updateByPrimaryKey(UserCoinEntity record);
}