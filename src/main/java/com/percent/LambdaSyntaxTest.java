package com.percent;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * lambda表达式的语法 () -> {}
 * 在jdk1.8中局部内部类使用局部变量不需要显示声明为final
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 13:03
 * @Description: lamda表达式本质上是语法糖，适用于匿名内部类，且为函数式接口（内部类中只有一个抽象方法）
 */
public class LambdaSyntaxTest {

    /**
     * 无参数，无返回值
     */
    @Test
    public void test1() {

        // 局部变量
        String prefix = "haha";
        // 局部内部类
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1 ..."+prefix);
            }
        };
        r1.run();
        System.out.println("==============");

        Runnable r2 = () -> System.out.println("r2 ...");
        r2.run();
    }

    /**
     * 一个参数，无返回值
     * 一个参数，()可以省略
     */
    @Test
    public void test2() {

        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer1.accept("consumer1");
        System.out.println("=============");

        Consumer<String> consumer2 = s -> System.out.println(s);
        consumer2.accept("consumer2");
    }

    /**
     * 多个参数，有返回值
     */
    @Test
    public void test3() {

        String prefix = "haha";

        // lambda体中多条{}不可省略
        Comparator<Integer> comparator1 = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1,o2);
        };
        int compare1 = comparator1.compare(1, 2);
        System.out.println(compare1);

        System.out.println("=================");

        // lambda体中一条{}可省略，同时return也可以省略
        Comparator<Integer> comparator2 = (o1,o2) -> Integer.compare(o1,o2);
        int compare2 = comparator2.compare(1, 2);
        System.out.println(compare2);

        System.out.println("=================");

        // (Integer o1, Integer o2) 参数列表的数据类型不需要显示声明，jdk会根据上下文 “类型推断”
        Comparator<Integer> comparator3 = (Integer o1, Integer o2) -> Integer.compare(o1,o2);
        int compare3 = comparator3.compare(1, 2);
        System.out.println(compare3);

        System.out.println("=================");
    }

    /**
     * 函数式接口
     * 使用@FunctionalInterface修饰
     */
    @Test
    public void test4() {

        Integer result = this.mathCalcul(4, t -> t * t);
        System.out.println(result);
    }

    private Integer mathCalcul(Integer t, Icalcul<Integer> icalcul) {
        return icalcul.calcul(t);
    }
}
