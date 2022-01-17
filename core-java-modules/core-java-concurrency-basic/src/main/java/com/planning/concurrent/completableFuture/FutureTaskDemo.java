package com.planning.concurrent.completableFuture;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 学习一下 FutureTask 的用法
 *
 * @author planning 2019.09.23
 */
public class FutureTaskDemo {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
            new ThreadFactoryBuilder().setNameFormat("planning-thread-%d").build());

    /*
        创建线程池的几种方式
        private static final int N_CPU = Runtime.getRuntime().availableProcessors();
        private ThreadPoolExecutor executor1 = new ThreadPoolExecutor(N_CPU + 1, N_CPU + 1, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(500), new ThreadFactoryBuilder().setNameFormat("planning-thread-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());
     */

    public static void main(String[] args) {

        // 在主线程中，启动 FutureTask 任务
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println("begin to cook food");
            Thread.sleep(5000);
            return "food cook is ok";
        });
        // 提交到 线程池 中执行
        // 也可以直接交给 new Thread(futureTask).start();
        executor.submit(futureTask);
        System.out.println("begin to make coke");
        try {
            // 主线程等待： assume make cook cost 3000ms
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("coke is ok");

        if (!futureTask.isDone()) {
            System.out.println("food is not ok, please wait 3000 ms");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
