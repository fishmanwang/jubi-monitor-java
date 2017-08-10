package com.jubi.dao.vo;

/**
 * Created by Administrator on 2017/8/10.
 */
public class CoinOrderNumVo {

    /**
     * 交易次数
     */
    private int count;

    /**
     * 交易总数
     */
    private long amount;

    /**
     * 交易总价值
     */
    private long total;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
