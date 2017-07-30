package com.jubi.util;

import com.jubi.exception.ApplicationException;
import com.jubi.exception.CommonErrorCode;

/**
 * 断言工具
 * Created by Administrator on 2017/7/30.
 */
public class AssertUtil {

    public static void asserTrue(boolean b, String msg) {
        if (!b) {
            throw new ApplicationException(CommonErrorCode.BIZ_ERROR, msg);
        }
    }

}
