/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author tjwang
 * @version $Id: UserCreateEvent.java, v 0.1 2017/8/22 0022 15:03 tjwang Exp $
 */
public class UserDeleteEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserDeleteEvent(Integer source) {
        super(source);
    }
}
