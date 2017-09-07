/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.dao;

import com.jubi.dao.vo.UserAdminVo;
import com.mybatis.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: UserExtDao.java, v 0.1 2017/9/6 0006 10:15 tjwang Exp $
 */
public interface UserExtDao {

    List<UserAdminVo> queryUser(@Param("name") String name, PageBounds pb);

}
