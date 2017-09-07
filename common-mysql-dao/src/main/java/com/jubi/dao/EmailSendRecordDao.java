package com.jubi.dao;

import com.jubi.dao.entity.EmailSendRecordEntity;
import com.jubi.dao.entity.EmailSendRecordEntityExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailSendRecordDao {
    int deleteByExample(EmailSendRecordEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmailSendRecordEntity record);

    int insertSelective(EmailSendRecordEntity record);

    List<EmailSendRecordEntity> selectByExampleWithPageBounds(EmailSendRecordEntityExample example, PageBounds pageBounds);

    List<EmailSendRecordEntity> selectByExample(EmailSendRecordEntityExample example);

    EmailSendRecordEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmailSendRecordEntity record, @Param("example") EmailSendRecordEntityExample example);

    int updateByExample(@Param("record") EmailSendRecordEntity record, @Param("example") EmailSendRecordEntityExample example);

    int updateByPrimaryKeySelective(EmailSendRecordEntity record);

    int updateByPrimaryKey(EmailSendRecordEntity record);
}