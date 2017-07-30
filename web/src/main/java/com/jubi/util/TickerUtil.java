package com.jubi.util;

/**
 * Created by Administrator on 2017/7/30.
 */
public class TickerUtil {

    public static String getTickerTableNameByCoin(String coin) {
        AssertUtil.asserTrue(coin.length() <= 10, "非法币种编号");
        return "jb_" + coin + "_ticker";
    }

}
