package com.percent;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 13:36
 * @Description:
 */
@FunctionalInterface
public interface Icalcul<T> {

    /**
     * 运算
     * @param t 传入的参数
     * @return T
     */
    T calcul(T t);
}
