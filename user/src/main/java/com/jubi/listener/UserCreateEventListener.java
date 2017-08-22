/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.listener;

import com.jubi.event.UserCreateEvent;
import com.jubi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

/**
 * @author tjwang
 * @version $Id: UserEventListener.java, v 0.1 2017/8/22 0022 15:04 tjwang Exp $
 */
public class UserCreateEventListener implements ApplicationListener<UserCreateEvent> {

    @Autowired
    private AccountService accountService;

    @Override
    public void onApplicationEvent(UserCreateEvent event) {
        Integer userId = (Integer) event.getSource();
        accountService.createAccount(userId.intValue());
    }

}
