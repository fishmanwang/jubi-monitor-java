package com.jubi.dao;

import com.jubi.dao.entity.PriceRateNotifyEntity;
import com.jubi.dao.entity.PriceRateNotifyEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PriceRateNotifyDao {
    int deleteByExample(PriceRateNotifyEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PriceRateNotifyEntity record);

    int insertSelective(PriceRateNotifyEntity record);

    List<PriceRateNotifyEntity> selectByExampleWithPageBounds(PriceRateNotifyEntityExample example, PageBounds pageBounds);

    List<PriceRateNotifyEntity> selectByExample(PriceRateNotifyEntityExample example);

    PriceRateNotifyEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PriceRateNotifyEntity record, @Param("example") PriceRateNotifyEntityExample example);

    int updateByExample(@Param("record") PriceRateNotifyEntity record, @Param("example") PriceRateNotifyEntityExample example);

    int updateByPrimaryKeySelective(PriceRateNotifyEntity record);

    int updateByPrimaryKey(PriceRateNotifyEntity record);
}