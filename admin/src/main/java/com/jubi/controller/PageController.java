/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author tjwang
 * @version $Id: PageController.java, v 0.1 2017/9/6 0006 10:49 tjwang Exp $
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/user")
    public ModelAndView goToUserPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user");
        return mv;
    }

}
