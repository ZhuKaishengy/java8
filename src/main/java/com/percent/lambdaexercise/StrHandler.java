package com.percent.lambdaexercise;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 14:09
 * @Description:
 */
@FunctionalInterface
public interface StrHandler {

    /**
     * 字符串的处理
     * @param str input
     * @return output
     */
    String handle(String str);
}
