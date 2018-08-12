package com.percent.lambdaexercise;

import com.percent.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 13:54
 * @Description: 关于lambda表达式的练习
 */
public class LambdaExerciseTest {

    // 初始化一些数据
    private ArrayList<Employee> employees = new ArrayList<>(5);
    {
        Employee employee1 = new Employee().setId(1).setAge(21).setSalary(BigDecimal.valueOf(10000));
        Employee employee2 = new Employee().setId(2).setAge(21).setSalary(BigDecimal.valueOf(4000));
        Employee employee3 = new Employee().setId(3).setAge(30).setSalary(BigDecimal.valueOf(5000));
        Employee employee4 = new Employee().setId(4).setAge(35).setSalary(BigDecimal.valueOf(6000));
        Employee employee5 = new Employee().setId(5).setAge(40).setSalary(BigDecimal.valueOf(7000));
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
    }
    /**
     * 调用Collections.sort()，定制排序两个Employee，先按年龄，年龄相同按salary比较
     */
    @Test
    public void test1() {

        employees.sort((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()) == 0 ? e1.getSalary().compareTo(e2.getSalary()) : 1);
        System.out.println(employees);
    }

    /**
     * 声明一个函数式接口用于字符串的处理
     */
    @Test
    public void test2() {

        String result = this.handleStr("   haha   ", str -> str.trim());
        System.out.println(result);
    }

    /**
     * 字符串的处理方法
     * @param str
     * @param handler
     * @return
     */
    private String handleStr(String str,StrHandler handler){
        return handler.handle(str);
    }

    /**
     * 声明两个带泛型的函数式接口，T为参数，R为返回值，计算两个long型数的和与乘机
     */
    @Test
    public void test3() {

        Long result1 = this.longCalcul(100L, 200L, (l1, l2) -> l1 + l2);
        System.out.println(result1);
        Long result2 = this.longCalcul(100L, 200L, (l1, l2) -> l1 - l2);
        System.out.println(result2);
    }

    private Long longCalcul(Long l1, Long l2, Calculator<Long, Long> calculator) {
        return calculator.calcul(l1,l2);
    }
}
