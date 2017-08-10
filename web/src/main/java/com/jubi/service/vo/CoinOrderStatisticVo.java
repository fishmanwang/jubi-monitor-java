package com.jubi.service.vo;

/**
 * 交易统计
 * Created by Administrator on 2017/8/10.
 */
public class CoinOrderStatisticVo {

    /**
     * 买单数
     */
    private long buyCount;

    /**
     * 买单量
     */
    private long buyAmount;

    /**
     * 买单总额
     */
    private long buyTotal;

    /**
     * 卖单数
     */
    private long sellCount;

    /**
     * 卖单量
     */
    private long sellAmount;

    /**
     * 卖单总额
     */
    private long sellTotal;

    public long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(long buyCount) {
        this.buyCount = buyCount;
    }

    public long getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(long buyAmount) {
        this.buyAmount = buyAmount;
    }

    public long getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(long buyTotal) {
        this.buyTotal = buyTotal;
    }

    public long getSellCount() {
        return sellCount;
    }

    public void setSellCount(long sellCount) {
        this.sellCount = sellCount;
    }

    public long getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(long sellAmount) {
        this.sellAmount = sellAmount;
    }

    public long getSellTotal() {
        return sellTotal;
    }

    public void setSellTotal(long sellTotal) {
        this.sellTotal = sellTotal;
    }
}
