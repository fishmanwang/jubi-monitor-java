package com.jubi.dao;

import com.jubi.dao.entity.PriceNotifyEntity;
import com.jubi.dao.entity.PriceNotifyEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PriceNotifyDao {
    int deleteByExample(PriceNotifyEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PriceNotifyEntity record);

    int insertSelective(PriceNotifyEntity record);

    List<PriceNotifyEntity> selectByExampleWithPageBounds(PriceNotifyEntityExample example, PageBounds pageBounds);

    List<PriceNotifyEntity> selectByExample(PriceNotifyEntityExample example);

    PriceNotifyEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PriceNotifyEntity record, @Param("example") PriceNotifyEntityExample example);

    int updateByExample(@Param("record") PriceNotifyEntity record, @Param("example") PriceNotifyEntityExample example);

    int updateByPrimaryKeySelective(PriceNotifyEntity record);

    int updateByPrimaryKey(PriceNotifyEntity record);
}