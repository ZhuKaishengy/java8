package com.percent.streamapi;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-9 17:21
 * @Description: stream api
 *
 * 数据源：集合、数组，转化成流
 * 传输：过程中进行一系列操作
 * 结果：产生一个全新的流，数据源不会发生改变
 *
 * 流程：创建stream->中间操作->终止流
 */
public class CreateStreamTest {

    /**
     * 初始化数据源
     */
    private List<String> list = new ArrayList<>(4);
    private String[] strs = new String[]{"a","b","c"};

    {
        list.add("zks");
        list.add("sjx");
        list.add("rl");
        list.add("haha");
    }

    /**
     * 创建流的几种方式
     * 1、通过Collections实例对象的stream()得到 串行流 ；parallelStream()得到并行流
     * 2、通过Arrays的静态方法stream()
     * 3、通过Stream类的of()
     * 4、创建无限流
     * 迭代
     * 生成
     */
    @Test
    public void testCreateStream(){

        Stream<String> stream1 = list.stream();
        Stream<String> stream2 = list.parallelStream();
        Stream<String> stream3 = Arrays.stream(strs);
        Stream<String> stream4 = Stream.of(this.strs);
        // 无限流 - 迭代
        Stream<Integer> stream5 = Stream.iterate(0, i -> i + 1);
        stream5.limit(10).forEach(System.out::println);
        System.out.println("========================================");
        // 无限流 - 生成
        Stream.generate(() -> new Random().nextInt(10))
                .limit(5).forEach(System.out::println);
    }
}
