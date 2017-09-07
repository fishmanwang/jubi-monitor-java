package com.jubi.dao;

import com.jubi.dao.entity.PriceWaveNotifyEntity;
import com.jubi.dao.entity.PriceWaveNotifyEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PriceWaveNotifyDao {
    int deleteByExample(PriceWaveNotifyEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PriceWaveNotifyEntity record);

    int insertSelective(PriceWaveNotifyEntity record);

    List<PriceWaveNotifyEntity> selectByExampleWithPageBounds(PriceWaveNotifyEntityExample example, PageBounds pageBounds);

    List<PriceWaveNotifyEntity> selectByExample(PriceWaveNotifyEntityExample example);

    PriceWaveNotifyEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PriceWaveNotifyEntity record, @Param("example") PriceWaveNotifyEntityExample example);

    int updateByExample(@Param("record") PriceWaveNotifyEntity record, @Param("example") PriceWaveNotifyEntityExample example);

    int updateByPrimaryKeySelective(PriceWaveNotifyEntity record);

    int updateByPrimaryKey(PriceWaveNotifyEntity record);
}