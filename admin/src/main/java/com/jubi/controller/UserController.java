/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.controller.vo.UserAdminView;
import com.jubi.dao.param.UserQueryParam;
import com.jubi.dao.vo.UserAdminVo;
import com.jubi.service.UserService;
import com.jubi.util.BeanMapperUtil;
import com.mybatis.domain.Paginator;
import com.mybatis.util.PageListHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: UserController.java, v 0.1 2017/9/6 0006 10:00 tjwang Exp $
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public RestResult queryUsers(UserQueryParam param) {
        List<UserAdminVo> ds = userService.queryUserAdminUser(param, getPageBounds());
        Paginator p = PageListHelper.getPaginator(ds);
        List<UserAdminView> rs = BeanMapperUtil.mapList(ds, UserAdminView.class);
        return RestResult.ok(PageListHelper.create(rs, p));
    }

}
