package com.jubi.dao;

import com.jubi.dao.entity.CoinRateEntity;
import com.mybatis.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 涨幅
 * Created by Administrator on 2017/7/30.
 */
public interface CoinRateDao {

    List<CoinRateEntity> queryCoinRates(@Param("coins") List<String> coin, @Param("span") int span, PageBounds pb);

}
