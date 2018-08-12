package com.percent.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 14:36
 * @Description:
 */
public class SimpleDateFormatThreadLocal {

    private static final ThreadLocal<SimpleDateFormat> THREADLOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

    public static Date parse(String str) throws ParseException {
        return THREADLOCAL.get().parse(str);
    }
}
