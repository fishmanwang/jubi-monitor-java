/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.dao;

import com.jubi.dao.entity.DepthWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: DepthExtDao.java, v 0.1 2017/8/7 0007 10:06 tjwang Exp $
 */
public interface DepthExtDao {

    List<DepthWithBLOBs> queryDepth(@Param("time") int time);

}
