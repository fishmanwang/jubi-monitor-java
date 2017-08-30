package com.jubi.event;

import com.jubi.event.param.FavoriteCoinChangeSource;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * 关注的虚拟币变化之后触发
 *
 * Created by Administrator on 2017/8/30.
 */
public class FavoriteCoinChangeEvent extends ApplicationEvent {

    public FavoriteCoinChangeEvent(FavoriteCoinChangeSource source) {
        super(source);
    }
}
