package com.jubi.service.vo;

/**
 * 币的时间+价格
 * Created by Administrator on 2017/7/30.
 */
public class TickerPriceVo {

    /**
     * 时间
     */
    private Integer pk;

    /**
     * 价格
     */
    private Double price;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
