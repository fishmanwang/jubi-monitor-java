package com.jubi.dao;

import com.jubi.dao.entity.TickerRateEntity;
import com.jubi.dao.vo.TickerRateSpanParam;
import com.mybatis.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 涨幅
 * Created by Administrator on 2017/7/30.
 */
public interface TickerRateExtDao {

    List<TickerRateEntity> queryTickerRate(@Param("param") TickerRateSpanParam param, PageBounds pb);

    /**
     * 大于一小时的间隔，需要减去8小时，从0点起。
     *
     * @param param
     * @param pb
     * @return
     */
    List<TickerRateEntity> queryHourTickerRate(@Param("param") TickerRateSpanParam param, @Param("limit") int limit);

}
