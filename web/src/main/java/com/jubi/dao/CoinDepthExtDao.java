/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.dao;

import com.jubi.dao.entity.CoinDepthWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: CoinDepthExtDao.java, v 0.1 2017/8/7 0007 10:06 tjwang Exp $
 */
public interface CoinDepthExtDao {

    List<CoinDepthWithBLOBs> queryDepth(@Param("time") int time);

}
