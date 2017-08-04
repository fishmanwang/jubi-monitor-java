package com.jubi.dao;

import com.jubi.dao.entity.CoinRateEntity;
import com.jubi.dao.vo.CoinRateSpanParam;
import com.mybatis.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 涨幅
 * Created by Administrator on 2017/7/30.
 */
public interface CoinRateDao {

    List<CoinRateEntity> queryCoinRates(@Param("param") CoinRateSpanParam param, PageBounds pb);

}
