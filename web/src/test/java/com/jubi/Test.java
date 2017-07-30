package com.jubi;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/30.
 */
public class Test {

    public static void main(String[] args) {
        long t = 1501344000000L;
        Date d = new Date(t);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(d));
    }

}
