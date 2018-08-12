package com.percent;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * 初识lambda表达式
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 10:29
 * @Description: lambda表达式用于优化内部类
 */
public class LambdaTest {

    /**
     * 传统的内部类
     */
    @Test
    public void test1() {

        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<Integer>(comparator);
    }

    /**
     * 使用lambda表达式优化内部类
     */
    @Test
    public void test2() {

        Comparator<Integer> comparator = (o1,o2) -> {return Integer.compare(o1,o2);};
    }

    // 初始化一些数据
    private ArrayList<Employee> employees = new ArrayList<>(5);
    {
        Employee employee1 = new Employee().setId(1).setAge(21).setSalary(BigDecimal.valueOf(3000));
        Employee employee2 = new Employee().setId(2).setAge(25).setSalary(BigDecimal.valueOf(4000));
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
     * 需求：返回员工年龄大于30岁的
     */
    @Test
    public void test3() {

        ArrayList<Employee> list = new ArrayList<>();
        for (Employee employee : this.employees) {
            if (employee.getAge() > 30) {
                list.add(employee);
            }
        }
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * 需求：返回工资大于四千的
     */
    @Test
    public void test4() {

        ArrayList<Employee> list = new ArrayList<>();
        for (Employee employee : this.employees) {
            if (employee.getSalary().compareTo(BigDecimal.valueOf(4000)) > 0) {
                list.add(employee);
            }
        }
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * 使用策略模式进行优化
     */
    @Test
    public void test5() {

        // 返回员工年龄大于30岁的
        List<Employee> employees1 = this.employeePredict(new EmployeeAgeMoreThenThirtyPredict());
        System.out.println(employees1);
        System.out.println("========================");
        // 返回工资大于四千的
        List<Employee> employees2 = this.employeePredict(new EmployeeSalaryMoreThenFortyPredict());
        System.out.println(employees2);
    }

    /**
     * 通过泛型抽取出来的方法
     * @param employeePredict 可以有多种实现
     * @return 过滤出来的结果
     */
    private List<Employee> employeePredict(Predict<Employee> employeePredict) {

        ArrayList<Employee> list = new ArrayList<>();
        for (Employee employee : this.employees) {
            if (employeePredict.match(employee)) {
                list.add(employee);
            }
        }
        return list;
    }

    /**
     * 使用策略模式+lambda表达式进行优化
     */
    @Test
    public void test6() {

        // 返回员工年龄大于30岁的
        List<Employee> employees1 = this.employeePredict((employee) -> employee.getAge() > 30);
        System.out.println(employees1);
        System.out.println("========================");
        // 返回工资大于四千的
        List<Employee> employees2 = this.employeePredict((employee) -> employee.getSalary().compareTo(BigDecimal
                .valueOf(4000)) > 0);
        System.out.println(employees2);
    }

    /**
     * 使用stream api进行优化
     */
    @Test
    public void test7() {

        this.employees.stream().filter(employee -> employee.getAge() > 30)
            .forEach(System.out::println);
    }
}
