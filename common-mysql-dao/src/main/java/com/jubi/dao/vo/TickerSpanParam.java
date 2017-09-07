package com.jubi.dao.vo;

/**
 * Created by Administrator on 2017/8/1.
 */
public class TickerSpanParam {

    private String coin;

    /**
     * 时间跨度，单位秒
     */
    private int span;

    /**
     * 开始时间
     */
    private Integer start;

    /**
     * 结束时间
     */
    private Integer end;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
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
