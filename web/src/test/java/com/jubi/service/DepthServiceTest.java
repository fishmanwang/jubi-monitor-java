/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.jubi.service.vo.DepthVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: DepthServiceTest.java, v 0.1 2017/8/7 0007 10:23 tjwang Exp $
 */
public class DepthServiceTest extends BaseServiceTest {

    @Autowired
    private DepthService depthService;

    @Test
    public void testQueryCurrentDepth() {
        List<DepthVo> ds = depthService.queryCurrentDepth();
        assertTrue(ds.size() > 0);
    }

}
