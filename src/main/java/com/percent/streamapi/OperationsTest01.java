package com.percent.streamapi;

import com.percent.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-9 18:13
 * @Description: 中间操作
 *
 * 惰性求值：没有终止操作，中间操作不会执行
 * 筛选与切片
 * filter:接受lambda，从流中排除某些元素
 * limit：截断流，使流中元素不超过某个数量
 * skip(n)：返回一个扔掉了前n个元素的流
 * distinct：通过流的equals和hashcode方法去重
 * 映射
 * map：接受lambda表达式，将元素转换为其他形式或提取信息，遍历元素，将function映射到每一个元素上
 * flatmap：接受一个函数作为参数，将流中每一个值都换成另一个流，然后把所有流连接成一个流
 * 排序
 * sorted：自然排序
 * sorted(Comparator com)：定制排序
 */
public class OperationsTest01 {

    /**
     * 初始化数据
     */
    private ArrayList<Employee> employees = new ArrayList<>(6);
    {
        Employee employee1 = new Employee().setId(1).setAge(21).setSalary(BigDecimal.valueOf(3000));
        Employee employee2 = new Employee().setId(2).setAge(25).setSalary(BigDecimal.valueOf(4000));
        Employee employee3 = new Employee().setId(3).setAge(30).setSalary(BigDecimal.valueOf(5000));
        Employee employee4 = new Employee().setId(4).setAge(35).setSalary(BigDecimal.valueOf(6000));
        Employee employee5 = new Employee().setId(5).setAge(40).setSalary(BigDecimal.valueOf(7000));
        Employee employee6 = new Employee().setId(5).setAge(40).setSalary(BigDecimal.valueOf(7000));
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
    }

    /**
     * filter：过滤
     */
    @Test
    public void testFilter(){

        // 内部迭代，employees的遍历有stream api完成
        employees.stream().filter((emp) -> emp.getAge() > 30)
                // 没有终止操作不会执行
                .forEach(System.out::println);
    }

    /**
     * limit：限制
     */
    @Test
    public void testLimit(){

        employees.stream().limit(1).forEach(System.out::println);
    }

    /**
     * skip:跳过
     */
    @Test
    public void testSkip(){

        employees.stream().skip(3).forEach(System.out::println);
    }

    /**
     * distinct:去重
     * employee需要重写hashcode和equals
     */
    @Test
    public void testDistinct(){

        employees.stream().distinct().forEach(System.out::println);
    }

    /**
     * map：映射
     */
    @Test
    public void testMap(){

        employees.stream().map(employee -> employee.setAge(employee.getAge() + 1))
                .forEach(System.out::println);

        employees.stream().map(Employee::getId).forEach(System.out::println);
    }

    @Test
    public void testFlatStream(){

        employees.stream().flatMap(employee -> this.getStream(String.valueOf(employee.getId())))
                .forEach(System.out::println);
    }
    private Stream<Character> getStream(String str){
        char[] chars = str.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char aChar : chars) {
            characters.add(aChar);
        }
        return characters.stream();
    }

    /**
     * sorted:自然排序
     */
    @Test
    public void testSort(){

        String[] strs = new String[]{"bbb","aaa","ddd","ccc"};
        Arrays.stream(strs).sorted().forEach(System.out::println);
    }

    /**
     * sorted：定制排序
     */
    @Test
    public void testSortComp(){

        employees.stream().sorted((employee1,employee2) -> Integer.compare(employee2.getId(),employee1.getId()))
                .distinct().forEach(System.out::println);
    }
}
