package com.jubi.dao;

import com.jubi.dao.entity.CoinDepth;
import com.jubi.dao.entity.CoinDepthExample;
import com.jubi.dao.entity.CoinDepthWithBLOBs;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoinDepthDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CoinDepthWithBLOBs record);

    int insertSelective(CoinDepthWithBLOBs record);

    List<CoinDepthWithBLOBs> selectByExampleWithBLOBsWithPageBounds(CoinDepthExample example, PageBounds pageBounds);

    List<CoinDepthWithBLOBs> selectByExampleWithBLOBs(CoinDepthExample example);

    List<CoinDepth> selectByExampleWithPageBounds(CoinDepthExample example, PageBounds pageBounds);

    List<CoinDepth> selectByExample(CoinDepthExample example);

    CoinDepthWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CoinDepthWithBLOBs record, @Param("example") CoinDepthExample example);

    int updateByExampleWithBLOBs(@Param("record") CoinDepthWithBLOBs record, @Param("example") CoinDepthExample example);

    int updateByExample(@Param("record") CoinDepth record, @Param("example") CoinDepthExample example);

    int updateByPrimaryKeySelective(CoinDepthWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CoinDepthWithBLOBs record);

    int updateByPrimaryKey(CoinDepth record);
}