package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.util.HttpClientUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/3.
 */
@RestController
@RequestMapping("/depth")
public class DepthController {

    @RequestMapping("/{coin}")
    public RestResult queryCoinDepth(@PathVariable("coin") String coin) {

        String url = "http://www.jubi.com/api/v1/depth/?coin=" + coin;
        String data = HttpClientUtil.get(url);

        return RestResult.ok(data);
    }


}
