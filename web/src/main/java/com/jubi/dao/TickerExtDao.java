package com.jubi.dao;

import com.jubi.dao.entity.TickerEntity;
import com.jubi.dao.vo.TickerSpanParam;
import com.mybatis.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
public interface TickerExtDao {

    List<TickerEntity> queryTickers(@Param("param") TickerSpanParam param, PageBounds pb);

}
