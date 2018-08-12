package com.percent.streamapi;

import com.percent.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-10 11:50
 * @Description: 终止操作
 * 查找与匹配
 * allMatch:检查是否匹配所有元素
 * anyMatch:检查是否至少匹配一个元素
 * noneMatch:检查是否没有匹配元素
 * findFirst:返回符合条件的第一个元素
 * findAny:返回符合条件的任意元素
 * count:返回符合条件的元素个数
 * max:返回符合条件的最大元素
 * min:返回符合条件的最小元素
 * 规约：
 * reduce：可以将流中元素反复结合起来，返回一个新值
 * 收集：
 * collect
 *
 */
public class EndOperation {
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
     * allMatch:检查是否匹配所有元素
     */
    @Test
    public void testAllMatch(){

        boolean b = employees.stream().distinct()
                .allMatch(emp -> emp.getSalary().intValue()>2000);
        System.out.println(b);
    }

    /**
     * anyMatch:检查是否至少匹配一个元素
     */
    @Test
    public void testAnyMatch(){

        boolean b = employees.stream().anyMatch(employee -> employee.getSalary().intValue() >= 7000);
        System.out.println(b);
    }
    /**
     * noneMatch:检查是否没有匹配元素
     */
    @Test
    public void testNoneMatch(){

        boolean b = employees.stream().noneMatch(employee -> employee.getSalary().intValue() > 7000);
        System.out.println(b);
    }

    /**
     * findFirst:返回符合条件的第一个元素
     */
    @Test
    public void testFindFirst(){

        Optional<Employee> optional = employees.stream().filter(employee -> employee.getSalary().intValue() >= 6000).distinct()
                .findFirst();
        optional.ifPresent(System.out::println);
    }
    /**
     * findAny:返回符合条件的任意元素
     */
    @Test
    public void testFindAny(){

        Optional<Employee> optional = employees.stream().filter(employee -> employee.getSalary().intValue() >= 6000).distinct()
                .findAny();
        optional.ifPresent(System.out::println);
    }
    /**
     * count:返回符合条件的元素个数
     */
    @Test
    public void testCount(){

        long count = employees.stream().filter(employee -> employee.getSalary().intValue() >= 6000).distinct()
                .count();
        System.out.println(count);
    }
    /**
     * max:返回符合条件的最大元素
     */
    @Test
    public void testMax(){

        Optional<Employee> max = employees.stream().filter(employee -> employee.getSalary().intValue() >= 6000).distinct()
                .max(Comparator.comparingInt(Employee::getAge));
        System.out.println(max);
    }
    /**
     * min:返回符合条件的最小元素
     */
    @Test
    public void testMin(){

        Optional<Employee> min = employees.stream().filter(employee -> employee.getSalary().intValue() >= 6000).distinct()
                .min(Comparator.comparingInt(Employee::getAge));
        System.out.println(min);
    }

    @Test
    public void test(){

        Optional<BigDecimal> min = employees.stream().map(Employee::getSalary)
                .min(BigDecimal::compareTo);
        min.ifPresent(System.out::println);
    }

    /**
     * reduce：可以将流中元素反复结合起来，返回一个新值
     */
    @Test
    public void testReduce1(){

        List<Integer> nums= Arrays.asList(1,2,3,4,5);
        Optional<Integer> optional = nums.stream().reduce((x, y) -> x + y);
        optional.ifPresent(System.out::println);
    }

    /**
     * 员工工资的总额
     */
    @Test
    public void testReduce2(){

        Optional<BigDecimal> reduce = employees.stream().map(Employee::getSalary)
                .reduce((x, y) -> BigDecimal.valueOf(x.intValue() + y.intValue()));
        reduce.ifPresent(System.out::println);
    }

    /**
     * collect:收集
     */
    @Test
    public void testCollector1(){

        List<BigDecimal> collect = employees.stream().map(Employee::getSalary)
                .collect(Collectors.toList());
        System.out.println(collect);

        System.out.println("=======================");

        Set<BigDecimal> collect1 = employees.stream().map(Employee::getSalary)
                .collect(Collectors.toSet());
        System.out.println(collect1);

        System.out.println("=======================");

        Set<BigDecimal> collect2 = employees.stream().map(Employee::getSalary)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect2);
    }

    @Test
    public void testCollector2(){

        // 总数
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println(count);
        // 平均值
        Double avg = employees.stream().collect(Collectors.averagingDouble((employee) -> employee.getSalary().doubleValue()));
        System.out.println(avg);
        // 总和
        Double sum = employees.stream().collect(Collectors.summingDouble((employee) -> employee.getSalary().doubleValue()));
        System.out.println(sum);
        // 最大值
        Optional<Employee> max = employees.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getId)));
        max.ifPresent(System.out::println);
        // 最小值
        Optional<Employee> min = employees.stream().collect(Collectors.minBy(Comparator.comparingInt(Employee::getId)));
        min.ifPresent(System.out::println);
        // 分组
        Map<Integer, List<Employee>> group1 = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(group1);
        // 多级分组
        Map<Integer, Map<String, List<Employee>>> group2 = employees.stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.groupingBy((Employee employee) -> {
            if (employee.getAge() > 25) {
                return "old";
            }
            return "young";
        })));
        System.out.println(group2);
        // 分区
        Map<Boolean, List<Employee>> part = employees.stream().collect(Collectors.partitioningBy((employee) -> employee.getAge() > 25));
        System.out.println(part);
        // 统计
        IntSummaryStatistics collect = employees.stream().collect(Collectors.summarizingInt(Employee::getAge));
        int max1 = collect.getMax();
        System.out.println(max1);
        // 操作
        String join1 = employees.stream().map((employee -> employee.getId().toString()))
                .collect(Collectors.joining(",", "==", "=="));
        System.out.println(join1);
    }



}

