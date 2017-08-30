package com.jubi.event.param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */
public class FavoriteCoinChangeSource {

    private int userId;

    private List<String> coins;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getCoins() {
        return coins;
    }

    public void setCoins(List<String> coins) {
        this.coins = coins;
    }
}
