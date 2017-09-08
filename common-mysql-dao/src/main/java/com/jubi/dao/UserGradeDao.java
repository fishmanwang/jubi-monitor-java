package com.jubi.dao;

import com.jubi.dao.entity.UserGrade;
import com.jubi.dao.entity.UserGradeExample;
import com.mybatis.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGradeDao {
    int deleteByExample(UserGradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserGrade record);

    int insertSelective(UserGrade record);

    List<UserGrade> selectByExampleWithPageBounds(UserGradeExample example, PageBounds pageBounds);

    List<UserGrade> selectByExample(UserGradeExample example);

    UserGrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserGrade record, @Param("example") UserGradeExample example);

    int updateByExample(@Param("record") UserGrade record, @Param("example") UserGradeExample example);

    int updateByPrimaryKeySelective(UserGrade record);

    int updateByPrimaryKey(UserGrade record);
}