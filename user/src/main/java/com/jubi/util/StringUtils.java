/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.util;

import java.util.Random;

/**
 *
 * @author tjwang
 * @version $Id: StringUtils.java, v 0.1 2017/8/4 0004 16:50 tjwang Exp $
 */
public class StringUtils {

    /**
     * 生成指定长度随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
