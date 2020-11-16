package com.percent.advance;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.Optional;

/**
 * @author : zhukaishengy
 * @date : 2020/11/16 10:18
 * @Description : {@link java.util.Optional}的使用
 * @version : v1.0
 */
@Slf4j
public class OptionalTest {

    /**
     * Optional构造器私有化，提供了三个静态方法用于初始化
     */
    @Test
    public void test1() {
        Person person1 = null;
        Person person2 = Person.builder().id(1).build();
        Optional<Object> empty = Optional.empty();
        // 入参必须不是null，否则报错
        Optional<Person> optional = Optional.of(person2);
        Optional<Person> optionalPerson = Optional.ofNullable(person1);
        log.info("empty:{}", empty.orElse(person2));
        log.info("optional:{}", optional.get());
        log.info("optionalPerson:{}", optionalPerson.orElseGet(Person::new));
    }

    /**
     * 取值
     */
    @Test
    public void test2() {
        Person person1 = null;
        Person person2 = Person.builder().id(1).build();
//        1.get()直接取，如果为null，就返回异常
        Assert.assertThrows(Exception.class, () -> Optional.ofNullable(person1).get());
//        2.orElse(T other)在取这个对象的时候，设置一个默认对象（默认值）；如果当前对象为null的时候，就返回默认对象
        Assert.assertEquals(person2, Optional.ofNullable(person1).orElse(person2));
//        3.orElseGet(Supplier<? extends T> other)跟第二个是一样的，区别只是参数，传入了一个函数式参数；
        Assert.assertEquals(person2, Optional.ofNullable(person1).orElseGet(() -> person2));
//        4. orElseThrow(Supplier<? extends X> exceptionSupplier)第四个，跟上面表达的是一样的，为null的时候，返回一个特定的异常；
        Assert.assertThrows(OutOfMemoryError.class, () -> Optional.ofNullable(person1).orElseThrow(OutOfMemoryError::new));
    }

    /**
     * 其他操作
     */
    @Test
    public void test3() {
        Person person1 = null;
        Person person2 = Person.builder().id(1).name("zks").build();
//        isPresent()是对当前的value进行null判断
        Assert.assertFalse(Optional.ofNullable(person1).isPresent());
        Assert.assertTrue(Optional.ofNullable(person2).isPresent());
//        ifPresent(Consumer<? super T> consumer)具体的意思，可以参看《JAVA8 Consumer接口》，理解了consumer接口，也就理解了这个方法；
        Optional.ofNullable(person1).ifPresent(person -> log.info("person:{}", person));
//        filter(Predicate<? super T> predicate)，map(Function<? super T, ? extends U> mapper) ，flatMap(Function<? super T, Optional<U>> mapper) 这几个方法的应用，可以参看我的《JAVA8 Stream接口，map操作，filter操作，flatMap操作》，跟stream的上的方法，用法是一致的，没有区别；
        String defaultValue = "xixixi";
        String name1 = Optional.ofNullable(person1).filter(person -> Objects.equals(person.getId(), 1)).map(Person::getName).orElse(defaultValue);
        String name2 = Optional.ofNullable(person2).filter(person -> Objects.equals(person.getId(), 1)).map(Person::getName).orElse(defaultValue);
        Assert.assertEquals(name1, defaultValue);
        Assert.assertEquals(name2, "zks");
    }


}
