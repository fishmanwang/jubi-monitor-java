/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.jubi.dao.DepthExtDao;
import com.jubi.dao.entity.DepthWithBLOBs;
import com.jubi.service.vo.DepthVo;
import com.jubi.util.BeanMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市场深度服务
 *
 * @author tjwang
 * @version $Id: DepthService.java, v 0.1 2017/8/7 0007 9:57 tjwang Exp $
 */
@Service
public class DepthService {

    @Autowired
    private DepthExtDao depthExtDao;

    public List<DepthVo> queryCurrentDepth() {
        List<DepthWithBLOBs> ds = depthExtDao.queryCurrentDepth();
        return BeanMapperUtil.mapList(ds, DepthVo.class);
    }

}
