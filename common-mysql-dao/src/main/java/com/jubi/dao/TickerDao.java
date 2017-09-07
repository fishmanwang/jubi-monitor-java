package com.jubi.dao;

import com.jubi.dao.entity.TickerEntity;
import com.jubi.dao.entity.TickerEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TickerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TickerEntity record);

    int insertSelective(TickerEntity record);

    List<TickerEntity> selectByExampleWithPageBounds(TickerEntityExample example, PageBounds pageBounds);

    List<TickerEntity> selectByExample(TickerEntityExample example);

    TickerEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TickerEntity record, @Param("example") TickerEntityExample example);

    int updateByExample(@Param("record") TickerEntity record, @Param("example") TickerEntityExample example);

    int updateByPrimaryKeySelective(TickerEntity record);

    int updateByPrimaryKey(TickerEntity record);
}