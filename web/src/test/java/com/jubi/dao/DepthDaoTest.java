/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.dao;

import com.jubi.dao.entity.DepthWithBLOBs;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: DepthDaoTest.java, v 0.1 2017/8/7 0007 10:13 tjwang Exp $
 */
public class DepthDaoTest extends BaseDaoTest {

    @Autowired
    private DepthExtDao depthExtDao;

    @Test
    public void testQueryCurrentDepth() {
        List<DepthWithBLOBs> ds = depthExtDao.queryCurrentDepth();
        assertTrue(ds.size() > 0);
    }

}
