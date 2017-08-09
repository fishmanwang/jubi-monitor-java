package com.jubi.dao;

import com.jubi.dao.entity.CoinOrderEntity;
import com.jubi.dao.entity.CoinOrderEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoinOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CoinOrderEntity record);

    int insertSelective(CoinOrderEntity record);

    List<CoinOrderEntity> selectByExampleWithPageBounds(CoinOrderEntityExample example, PageBounds pageBounds);

    List<CoinOrderEntity> selectByExample(CoinOrderEntityExample example);

    CoinOrderEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CoinOrderEntity record, @Param("example") CoinOrderEntityExample example);

    int updateByExample(@Param("record") CoinOrderEntity record, @Param("example") CoinOrderEntityExample example);

    int updateByPrimaryKeySelective(CoinOrderEntity record);

    int updateByPrimaryKey(CoinOrderEntity record);
}