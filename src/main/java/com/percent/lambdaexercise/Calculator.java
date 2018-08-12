package com.percent.lambdaexercise;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 14:15
 * @Description:
 */
@FunctionalInterface
public interface Calculator<T,R> {

    /**
     * 计算两个数的各种运算
     * @param t1 param1
     * @param t2 param2
     * @return result
     */
    R calcul(T t1, T t2);
}
