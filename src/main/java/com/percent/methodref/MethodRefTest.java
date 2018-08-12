package com.percent.methodref;

import com.percent.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-9 16:24
 * @Description: 方法引用
 *
 * （一）方法引用：
 * 在lambda体中的内容已经有方法实现了，可以使用方法引用（可以理解为lambda表达式的另一种形式）
 * 条件：实现的方法的参数列表和返回值与函数接口的参数列表和返回值保持一致
 * 三种语法格式：
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 * （二）构造器引用
 * 使用那个构造器取决于函数接口的参数列表
 * classname::new
 * （三）数组引用
 * Type::new
 */
public class MethodRefTest {

    /**
     * 对象::实例方法名
     */
    @Test
    public void test1(){

        String str = "haha";
        Consumer<String> consumer1 = (tmp) -> System.out.println(tmp);
        consumer1.accept(str);

        Consumer<String> consumer2 = System.out::println;
        consumer1.accept(str);

        Supplier<Long> supplier1 = () -> System.currentTimeMillis();
        Supplier<Long> supplier2 = System::currentTimeMillis;
    }
    /**
     * 类::静态方法名
     */
    @Test
    public void test2(){

        Comparator<Integer> comparator1 = (a,b) -> Integer.compare(a,b);
        Comparator<Integer> comparator2 = Integer::compare;
    }
    /**
     * 类::实例方法名
     * 第一个参数为方法的调用者，第二个参数为方法的参数
     */
    @Test
    public void test3(){
        BiPredicate<String,String> integerIntegerBiPredicate1 = (a, b) -> a.equals(b);
        BiPredicate<String,String> integerIntegerBiPredicate2 = String::equals;
    }

    /**
     * 构造器引用
     */
    @Test
    public void test4(){

        Supplier<Employee> employeeSupplier1 = () -> new Employee();
        Supplier<Employee> employeeSupplier2 = Employee::new;
    }

    /**
     * 数组引用
     */
    @Test
    public void test5(){

        Function<Integer,String[]> integerFunction1 = num -> new String[num];
        Function<Integer,String[]> integerFunction2 = String[]::new;
    }
}
