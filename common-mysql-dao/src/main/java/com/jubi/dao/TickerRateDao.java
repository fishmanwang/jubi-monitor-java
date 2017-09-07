package com.jubi.dao;

import com.jubi.dao.entity.TickerRateEntity;
import com.jubi.dao.entity.TickerRateEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TickerRateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TickerRateEntity record);

    int insertSelective(TickerRateEntity record);

    List<TickerRateEntity> selectByExampleWithPageBounds(TickerRateEntityExample example, PageBounds pageBounds);

    List<TickerRateEntity> selectByExample(TickerRateEntityExample example);

    TickerRateEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TickerRateEntity record, @Param("example") TickerRateEntityExample example);

    int updateByExample(@Param("record") TickerRateEntity record, @Param("example") TickerRateEntityExample example);

    int updateByPrimaryKeySelective(TickerRateEntity record);

    int updateByPrimaryKey(TickerRateEntity record);
}