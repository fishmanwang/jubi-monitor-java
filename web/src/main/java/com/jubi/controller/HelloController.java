/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tjwang
 * @version $Id: HelloController.java, v 0.1 2017/7/28 0028 11:43 tjwang Exp $
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/index.do")
    public String hello() {
        return "index";
    }

}
