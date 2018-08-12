package com.percent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 12:53
 * @Description: 使用forkjoin框架计算1~100000000累加
 * 使用工作窃取模式（working-steal）
 */
public class ForkJoinCalculator extends RecursiveTask<Long>{

    private long start;
    private long end;
    private static final int BOUND = 1000;

    ForkJoinCalculator(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end -start;
        if (length <= BOUND) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        long middle = ( start + end )/ 2;

        ForkJoinCalculator forkJoinCalculatorLeft = new ForkJoinCalculator(start, middle);
        forkJoinCalculatorLeft.fork();
        ForkJoinCalculator forkJoinCalculatorRight = new ForkJoinCalculator(middle+1, end);
        forkJoinCalculatorRight.fork();

        return forkJoinCalculatorLeft.join() + forkJoinCalculatorRight.join();
    }
}
