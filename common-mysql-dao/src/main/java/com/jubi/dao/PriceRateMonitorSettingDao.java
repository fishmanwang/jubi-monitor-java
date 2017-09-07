package com.jubi.dao;

import com.jubi.dao.entity.PriceRateMonitorSettingEntity;
import com.jubi.dao.entity.PriceRateMonitorSettingEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PriceRateMonitorSettingDao {
    int deleteByExample(PriceRateMonitorSettingEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PriceRateMonitorSettingEntity record);

    int insertSelective(PriceRateMonitorSettingEntity record);

    List<PriceRateMonitorSettingEntity> selectByExampleWithPageBounds(PriceRateMonitorSettingEntityExample example, PageBounds pageBounds);

    List<PriceRateMonitorSettingEntity> selectByExample(PriceRateMonitorSettingEntityExample example);

    PriceRateMonitorSettingEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PriceRateMonitorSettingEntity record, @Param("example") PriceRateMonitorSettingEntityExample example);

    int updateByExample(@Param("record") PriceRateMonitorSettingEntity record, @Param("example") PriceRateMonitorSettingEntityExample example);

    int updateByPrimaryKeySelective(PriceRateMonitorSettingEntity record);

    int updateByPrimaryKey(PriceRateMonitorSettingEntity record);
}