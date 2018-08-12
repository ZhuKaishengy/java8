package com.percent;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 10:56
 * @Description:
 */
public interface Predict<T> {

    /**
     * 是否匹配
     * @param t 泛型
     * @return true / false
     */
    boolean match(T t);
}
