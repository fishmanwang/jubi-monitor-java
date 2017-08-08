/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.jubi.dao.DepthDao;
import com.jubi.dao.DepthExtDao;
import com.jubi.dao.entity.DepthExample;
import com.jubi.dao.entity.DepthWithBLOBs;
import com.jubi.service.vo.DepthVo;
import com.jubi.util.BeanMapperUtil;
import com.mybatis.domain.PageBounds;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    private DepthDao depthDao;

    @Autowired
    private DepthExtDao depthExtDao;

    public List<DepthVo> queryDepth(int year, int month, int day, int hour, int minute) {
        DateTime dateTime = new DateTime(year, month, day, hour, minute, 0);
        int time = Long.valueOf(dateTime.getMillis() / 1000).intValue();
        List<DepthWithBLOBs> ds = depthExtDao.queryDepth(time);
        return BeanMapperUtil.mapList(ds, DepthVo.class);
    }

    public List<DepthVo> queryCoinDepth(String coin, Date begin) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coin));
        Preconditions.checkNotNull(begin, "开始时间不能为空");

        int beginTime = Long.valueOf(begin.getTime() / 1000).intValue();

        DepthExample exam = new DepthExample();
        exam.createCriteria().andCoinEqualTo(coin).andPkGreaterThanOrEqualTo(beginTime);

        PageBounds pb = new PageBounds(1, 100, false);
        List<DepthWithBLOBs> ds = depthDao.selectByExampleWithBLOBsWithPageBounds(exam, pb);
        return BeanMapperUtil.mapList(ds, DepthVo.class);
    }

}
