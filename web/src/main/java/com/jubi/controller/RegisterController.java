/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.service.UserService;
import com.jubi.service.vo.UserRegisterParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author tjwang
 * @version $Id: RegisterController.java, v 0.1 2017/3/23 0023 16:44 tjwang Exp $
 */
@RestController
public class RegisterController {

    @Resource
    private UserService userService;

    //@RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestResult register(@Valid UserRegisterParam param) {
        userService.registerUser(param);
        return RestResult.ok();
    }

}
