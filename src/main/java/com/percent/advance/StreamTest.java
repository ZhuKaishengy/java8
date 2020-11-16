package com.percent.advance;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : zhukaishengy
 * @version : v1.0
 * @date : 2020/11/16 15:48
 * @Description : stream流接口
 */
@Slf4j
public class StreamTest {

    /**
     * 创建流的几种方式
     */
    @Test
    public void test1() {
        /*
            有限流
         */
        // 1.Arrays.stream，我们可以通过Arrays的静态方法，传入一个泛型数组，创建一个流
        int[] ints = new int[]{1, 2, 3};
        int max1 = Arrays.stream(ints).map(item -> item += 10).max().getAsInt();
        Assert.assertEquals(max1, 13);
        // 2.Stream.of，我们可以通过Stream的静态方法，传入一个泛型数组，或者多个参数，创建一个流，这个静态方法，也是调用了Arrays的stream静态方法
        int max2 = Stream.of(1, 2, 3).mapToInt(item -> item += 10).max().getAsInt();
        Assert.assertEquals(max2, 13);
        // 3.Collection.stream,可以用过集合的接口的默认方法，创建一个流；使用这个方法，包括继承Collection的接口，如：Set，List，Map，SortedSet 等等
        List<String> list = new ArrayList<>(6);
        list.add("a");
        list.add("b");
        list.add("c");
        String str = list.stream().filter(item -> Objects.equals(item, "b")).findAny().orElse("none");
        Assert.assertEquals(str, "b");
        /*
            无限流
         */
        // 4.Stream.iterate，是Stream接口下的一个静态方法，从名字也可以看出，这个静态方法，是以迭代器的形式，创建一个数据流
        Person seed = Person.builder().id(1).name("zks1").build();
        Stream.iterate(seed, person -> {
            int id = person.getId();
            String name = person.getName();
            String prefix = name.substring(0, name.length() - 1);
            int suffix = id + 1;
            return Person.builder().id(id + 1).name(prefix + suffix).build();
        }).limit(10).forEach(person -> log.info("person:{}", person));

        log.info("===========================");

        // 5.Stream.generate，也是stream中的一个静态方法
        Stream.generate(() -> {
            Random random = new Random();
            int id = random.nextInt(11);
            return Person.builder().id(id).name("zks" + id).build();
        }).limit(10).forEach(person -> log.info("person:{}", person));
    }

    private List<Person> generatePersons(int limit) {
        Person seed = Person.builder().id(1).name("zks1").build();
        return Stream.iterate(seed, person -> {
            int id = person.getId();
            String name = person.getName();
            String prefix = name.substring(0, name.length() - 1);
            int suffix = id + 1;
            return Person.builder().id(id + 1).name(prefix + suffix).build();
        }).limit(limit).collect(Collectors.toList());
    }

    private List<Person> generateRandomPersons(int limit) {
        return Stream.generate(() -> {
            Random random = new Random();
            int id = random.nextInt(11);
            return Person.builder().id(id).name("zks" + id).build();
        }).limit(limit).collect(Collectors.toList());
    }

    /**
     * 中间操作：filter、map、flatMap
     */
    @Test
    public void test2() {
        // filter
        Stream.of("a", 1, 2).filter(item -> item instanceof Integer).forEach(System.out::println);
        // map
        List<Person> people = this.generatePersons(10);
        people.stream().map(Person::getName).forEach(System.out::println);
        // flatMap 作用是把两个流，变成一个流返回
        people.stream().map(person -> {
            List<Object> list = new ArrayList<>();
            list.add(person.getId());
            list.add(person.getName());
            return list;
        }).flatMap(Collection::stream).forEach(System.out::println);
    }

    /**
     * 中间操作：distinct，sorted，peek(对流中的item进行操作)，limit，skip（忽略前N个对象）
     */
    @Test
    public void test3() {
        List<Person> people = this.generateRandomPersons(10);
        // distinct
        people.stream().distinct().forEach(System.out::println);
        log.info("===============================");
        // sorted
        people.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).forEach(System.out::println);
        log.info("===============================");
        // peek
        people.stream().distinct().sorted(Comparator.comparingInt(Person::getId)).filter(person -> person.getId() > 5)
                .peek(person -> person.setName("good" + person.getName())).forEach(System.out::println);
        log.info("===============================");
        // limit skip
        List<Person> people1 = this.generatePersons(10);
        people1.stream().sorted(Comparator.comparingInt(Person::getId)).skip(3).limit(5).forEach(System.out::println);
    }

    /**
     * 终端操作: forEach、forEachOrdered
     */
    @Test
    public void test4() {
        List<Person> people = this.generatePersons(5);
        // forEach
        people.stream().forEach(System.out::println);
        log.info("===============================");
        people.parallelStream().forEach(System.out::println);
        log.info("===============================");
        // forEachOrdered 保证并行流顺序
        people.stream().forEachOrdered(System.out::println);
        log.info("===============================");
        people.parallelStream().forEachOrdered(System.out::println);
    }

    /**
     * 终端操作: toArray操作
     */
    @Test
    public void test5() {
        List<Person> people = this.generatePersons(5);
        Object[] objs = people.stream().toArray();
        Person[] people1 = people.stream().toArray(person -> new Person[people.size()]);
        Person[] people2 = people.stream().toArray(Person[]::new);
        log.info("objects:{}", objs.length);
        log.info("people1:{}", people1.length);
        log.info("people2:{}", people2.length);
    }

    /**
     * 终端操作: min，max，findFirst，findAny操作
     */
    @Test
    public void test6() {
        List<Person> people = this.generateRandomPersons(10);
        Person minPerson = people.stream().min(Comparator.comparingInt(Person::getId)).orElseGet(Person::new);
        Person maxPerson = people.stream().max(Comparator.comparingInt(Person::getId)).orElseGet(Person::new);
        log.info("min:{}, max:{}", minPerson, maxPerson);

        Person person1 = people.stream().filter(person -> person.getId() > 5).peek(person -> person.setName("lululu")).findFirst().orElse(null);
        Person person2 = people.stream().filter(person -> person.getId() > 5).peek(person -> person.setName("lululu")).findAny().orElse(null);
        log.info("person1:{}, person2:{}", person1, person2);
    }

    /**
     * 终端操作: count，anyMatch，allMatch，noneMatch
     */
    @Test
    public void test7() {
        List<Person> people = this.generateRandomPersons(10);
        long count = people.stream().filter(person -> person.getId() > 5).count();
        boolean b1 = people.stream().anyMatch(Objects::isNull);
        boolean b2 = people.stream().allMatch(Objects::nonNull);
        boolean b3 = people.stream().noneMatch(Objects::isNull);
        log.info("count:{}, b1:{}, b2:{}, b3:{}", count, b1, b2, b3);
    }

    /**
     * 终端操作: reduce操作
     * 三个参数的重载方法用来解决fork join并发框架的问题
     */
    @Test
    public void test8() {
        List<Person> people = this.generatePersons(5);
        // 第一个参数是上次函数执行的返回值（也称为中间结果），第二个参数是stream中的元素，这个函数把这两个值相加，得到的和会被赋值给下次执行这个函数的第一个参数。
        // 要注意的是：第一次执行的时候第一个参数的值是Stream的第一个元素，第二个参数是Stream的第二个元素
        Person person = people.stream().reduce((p1, p2) -> p1.getId() > p2.getId() ? p1 : p2).orElse(null);
        log.info("person:{}", person);
        // identity: 参与运算的初始值，若people为null，返回identity
        Integer sum = people.stream().map(Person::getId).reduce(100, Integer::sum);
        log.info("sum:{}", sum);
    }



}
