package com.jubi.dao.vo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
public class CoinRateSpanParam {

    private List<String> coins;

    /**
     * 时间跨度，单位秒
     */
    private int          span;

    /**
     * 开始时间
     */
    private Integer      start;

    /**
     * 结束时间
     */
    private Integer      end;

    public List<String> getCoins() {
        return coins;
    }

    public void setCoins(List<String> coins) {
        this.coins = coins;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
