package com.percent.forkjoin;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.InitParam;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 13:07
 * @Description:
 * 5000000050000000
    328
    5000000050000000
    1357
    5000000050000000
    147
 */
public class ForkJoinCalculatorTest {


    @Test
    public void test1(){

        Instant start = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long result = forkJoinPool.invoke(new ForkJoinCalculator(0L, 100000000L));
        Instant end = Instant.now();
        System.out.println(result);
        System.out.println(Duration.between(start,end).toMillis());
    }

    @Test
    public void test2(){

        Instant start = Instant.now();
        Long result = 0L;
        for (Long i = 0L; i <= 100000000L; i++) {
            result += i;
        }
        System.out.println(result);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
    }

    @Test
    public void test3(){
        Instant start = Instant.now();
        OptionalLong reduce = LongStream.rangeClosed(0L, 100000000L)
                .parallel()
                .reduce(Long::sum);
        System.out.println(reduce.getAsLong());
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
    }

}
