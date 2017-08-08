package com.jubi.dao;

import com.jubi.dao.entity.Depth;
import com.jubi.dao.entity.DepthExample;
import com.jubi.dao.entity.DepthWithBLOBs;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepthDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DepthWithBLOBs record);

    int insertSelective(DepthWithBLOBs record);

    List<DepthWithBLOBs> selectByExampleWithBLOBsWithPageBounds(DepthExample example, PageBounds pageBounds);

    List<DepthWithBLOBs> selectByExampleWithBLOBs(DepthExample example);

    List<Depth> selectByExampleWithPageBounds(DepthExample example, PageBounds pageBounds);

    List<Depth> selectByExample(DepthExample example);

    DepthWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DepthWithBLOBs record, @Param("example") DepthExample example);

    int updateByExampleWithBLOBs(@Param("record") DepthWithBLOBs record, @Param("example") DepthExample example);

    int updateByExample(@Param("record") Depth record, @Param("example") DepthExample example);

    int updateByPrimaryKeySelective(DepthWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DepthWithBLOBs record);

    int updateByPrimaryKey(Depth record);
}