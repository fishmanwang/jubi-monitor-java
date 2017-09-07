/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.listener;

import com.jubi.event.UserDeleteEvent;
import com.jubi.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author tjwang
 * @version $Id: UserDeleteListener.java, v 0.1 2017/9/7 0007 13:47 tjwang Exp $
 */
@Component
public class UserDeleteListener implements ApplicationListener<UserDeleteEvent> {

    @Autowired
    private UserAdminService userAdminService;

    @Override
    public void onApplicationEvent(UserDeleteEvent event) {
        Integer userId = (Integer) event.getSource();
        userAdminService.deleteUserAssociation(userId);
    }


}
