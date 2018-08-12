package com.percent.interfac;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 13:44
 * @Description:
 */
public interface MyFunc {

    /**
     * 默认方法
     * @return
     */
    default String getName(){
        return "haha";
    }
}
