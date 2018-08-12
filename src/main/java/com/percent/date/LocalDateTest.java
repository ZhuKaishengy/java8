package com.percent.date;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 15:31
 * @Description:
 */
public class LocalDateTest {

    /**
     * localDate
     * localTime
     * LocalDateTime
     */
    @Test
    public void test1(){

        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(2011,1,1,1,1,1,1);
        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        LocalDateTime plusYears = localDateTime1.plusYears(2);
        System.out.println(plusYears);
        System.out.println(plusYears.getYear());
        System.out.println(plusYears.getMonth().getValue());
        System.out.println(plusYears.getDayOfMonth());
    }

    /**
     * instant:时间戳
     */
    @Test
    public void test2(){

        Instant instant = Instant.now();
        long l = instant.toEpochMilli();
        System.out.println(l);
        System.out.println(System.currentTimeMillis());
    }

    /**
     * duration：两个时间间隔
     * period：两个日期间隔
     */
    @Test
    public void test3() throws InterruptedException {

        Instant instant1 = Instant.now();
        Thread.sleep(1000);
        Instant instant2 = Instant.now();
        Duration duration = Duration.between(instant1, instant2);
        long millis = duration.toMillis();
        System.out.println(millis);

        System.out.println("===============================");
        LocalDateTime localDateTime1 = LocalDateTime.now();
        Thread.sleep(1000);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        Duration duration2 = Duration.between(localDateTime1, localDateTime2);
        long millis2 = duration.toMillis();
        System.out.println(millis2);
    }

    /**
     * TemporalAdjuster：时间校正器
     */
    @Test
    public void test4(){

        LocalDateTime localDateTime = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(localDateTime);
    }

    /**
     * DateTimeFormatter:日期格式化
     */
    @Test
    public void test5(){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String str = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println(str);
        TemporalAccessor parse = dateTimeFormatter.parse(str);
        System.out.println(LocalDateTime.from(parse));
//        LocalDateTime parse = LocalDateTime.parse("2011-01-01", dateTimeFormatter);
//        System.out.println(parse);
    }
}
