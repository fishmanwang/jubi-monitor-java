package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.service.EmailSendRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/3.
 */
@RestController
@RequestMapping("/email")
public class EmailSendRecordController extends AbstractController {

    @Autowired
    private EmailSendRecordService emailSendRecordService;

    @RequestMapping("/record")
    public RestResult queryRecentEmailSendRecord() {
        Integer userId = getUser().getId();

        return RestResult.ok(emailSendRecordService.queryRecentRecord(userId));
    }

}
