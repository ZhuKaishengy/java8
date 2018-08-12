package com.percent.date;

import org.junit.Test;

import javax.sql.PooledConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 14:26
 * @Description: SimpleDateFormat存在线程安全问题
 */
public class SimpleDateFormatTest {

    @Test
    public void test1() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse("20180101");
        System.out.println(date);
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Date> task = () -> simpleDateFormat.parse("20180101");

        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Future<Date> future = executorService.submit(task);
            results.add(future);
        }
        for (Future<Date> result : results) {

            System.out.println(result.get());
        }

    }
    @Test
    public void test3() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Date> task = () -> SimpleDateFormatThreadLocal.parse("20180101");

        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Future<Date> future = executorService.submit(task);
            results.add(future);
        }
        for (Future<Date> result : results) {

            System.out.println(result.get());
        }

    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<LocalDate> task = () -> LocalDate.parse("20180101",dateTimeFormatter);

        List<Future<LocalDate>> results = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Future<LocalDate> future = executorService.submit(task);
            results.add(future);
        }
        for (Future<LocalDate> result : results) {

            System.out.println(result.get());
        }

    }
}
