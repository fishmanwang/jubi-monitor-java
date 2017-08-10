package com.jubi.dao;

import com.jubi.dao.vo.CoinOrderNumVo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/8/10.
 */
public interface CoinOrderExtDao {

    /**
     * 查询指定时间之后的行情
     * @param coin
     * @param time
     * @return
     */
    CoinOrderNumVo queryStatistic(@Param("coin")String coin, @Param("time")int time,
                                        @Param("plus") boolean plus);

}
