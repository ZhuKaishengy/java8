package com.percent.interfac;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 13:48
 * @Description: 类优先原则
 */
public class MyTest extends Func implements MyFunc {

    public static void main(String[] args) {
        String name = new MyTest().getName();
        System.out.println(name);
    }
}
