package com.percent.streamexercise;

import com.percent.Employee;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 10:32
 * @Description: stream api 练习
 */
public class StreamApiTest {
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
     * 给定一个数字列表，返回数字平方列表
     */
    @Test
    public void test1() {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        List<Integer> collect = list.stream().map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * map中有多少employee
     */
    @Test
    public void test2(){

        long count = employees.stream().count();
        System.out.println(count);
    }

    List<Transaction> transactions = null;
    @Before
    public void initTransaction(){

        Trader trader1 = new Trader("zks","sy");
        Trader trader2 = new Trader("sjx","sy");
        Trader trader3 = new Trader("haha","bj");
        Trader trader4 = new Trader("xixi","sy");

        Transaction transaction1 = new Transaction(trader1,2011,300d);
        Transaction transaction2 = new Transaction(trader2,2012,400d);
        Transaction transaction3 = new Transaction(trader3,2013,500d);
        Transaction transaction4 = new Transaction(trader4,2014,600d);
        Transaction transaction5 = new Transaction(trader1,2011,700d);
        Transaction transaction6 = new Transaction(trader1,2011,800d);
        transactions = Arrays.asList(transaction1,transaction2,transaction3,transaction4,transaction5,transaction6);
    }

    /**
     * 2011年发生的所有交易，按交易额的顺序（从低到高）
     */
    @Test
    public void test3(){

        List<Transaction> list = transactions.stream()
                .filter(transaction -> Objects.equals(transaction.getYear(), 2011))
                .sorted(Comparator.comparingDouble(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(list);
    }
    /**
     * 交易员都在哪些不同的城市工作过
     */
    @Test
    public void test4() {

        List<String> list = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);
    }
    /**
     * 查找所有沈阳的交易员，按姓名排序
     */
    @Test
    public void test5(){

        List<Trader> list = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> Objects.equals(trader.getCity(), "sy"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(list);
    }
    /**
     * 返回所有交易员姓名的字符串，按字母顺序排序
     */
    @Test
    public void test6(){

        String str = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .collect(Collectors.joining(",", "==", "=="));
        System.out.println(str);
    }
    /**
     * 有没有再bj工作的交易员
     */
    @Test
    public void test7(){

        boolean bj = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> Objects.equals(trader.getCity(), "bj"));
        System.out.println(bj);
    }
    /**
     * sy交易员的所有交易额
     */
    @Test
    public void test8(){

        double sy = transactions.stream()
                .filter((transaction -> Objects.equals(transaction.getTrader().getCity(), "sy")))
                .mapToDouble(Transaction::getValue).sum();
        System.out.println(sy);
    }
    /**
     * 最高交易额
     */
    @Test
    public void test9(){

        Optional<Double> d = transactions.stream()
                .map(Transaction::getValue)
                .max(Double::compare);
        Double aDouble = d.get();
        System.out.println(aDouble);
    }
    /**
     * 交易额最小的交易
     */
    @Test
    public void test10(){

        Optional<Transaction> minTransaction = transactions.stream()
                .min(Comparator.comparingDouble(Transaction::getValue));
        System.out.println(minTransaction.get());

    }

}
