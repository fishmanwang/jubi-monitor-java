package com.jubi.service;

import com.google.common.base.Preconditions;
import com.jubi.dao.EmailSendRecordDao;
import com.jubi.dao.entity.EmailSendRecordEntity;
import com.jubi.dao.entity.EmailSendRecordEntityExample;
import com.jubi.service.vo.EmailSendRecordVo;
import com.jubi.util.BeanMapperUtil;
import com.mybatis.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 邮件发送记录
 *
 * Created by Administrator on 2017/9/3.
 */
@Service
public class EmailSendRecordService {

    @Autowired
    private EmailSendRecordDao emailSendRecordDao;

    public List<EmailSendRecordVo> queryRecentRecord(Integer userId) {
        Preconditions.checkNotNull(userId, "用户不能为空");

        EmailSendRecordEntityExample exam = new EmailSendRecordEntityExample();
        exam.createCriteria().andUserIdEqualTo(userId);

        PageBounds pb = new PageBounds(1, 50, false);

        List<EmailSendRecordEntity> ds = emailSendRecordDao.selectByExampleWithPageBounds(exam, pb);

        return BeanMapperUtil.mapList(ds, EmailSendRecordVo.class);
    }

}
