package com.jubi.dao;

import com.jubi.entity.TickerEntity;
import com.mybatis.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
public interface TickerDao {

    List<TickerEntity> queryTickers(@Param("coin") String coin, @Param("span") int span, PageBounds pb);

}
