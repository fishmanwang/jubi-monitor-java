package com.jubi.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/1.
 */
public class DateUtils {

    public static final Integer DAY_LONG = 86400;

    /**
     * 获取当天凌晨12点整的秒数
     * @return
     */
    public static int getCurrentDayBeginTime() {
        Date date = new Date();
        return getDayBeginTime(date);
    }

    /**
     * 获取指定时间所在日的凌晨12点整的秒数
     * @param date: 任意日期
     * @return
     */
    public static int getDayBeginTime(Date date) {
        int t = Long.valueOf(date.getTime() / 1000).intValue();
        //t = t - (t % DateUtils.DAY_LONG);
        t = Long.valueOf(org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.DAY_OF_MONTH).getTime() / 1000).intValue();
        return t;
    }

}
