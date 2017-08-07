package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.service.DepthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/3.
 */
@RestController
@RequestMapping("/depth")
public class DepthController {

    @Autowired
    private DepthService depthService;

    @RequestMapping("/current")
    public RestResult queryCoinDepth() {
        return RestResult.ok(depthService.queryCurrentDepth());
    }


}
