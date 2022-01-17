package com.planning.concurrent.completableFuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Completable demo
 *
 * @author yxc
 * @since 2020-06-18 16:55
 **/
public class CompletableFutureDemo {

    /**
     * 一个简单的例子
     */
    @Test
    public void test1() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Future's Result");

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 runAsync() 运行异步计算（不返回结果）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running job
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });

        // Block and wait for the future to complete
        System.out.println(future.get());
    }

    /**
     * 使用 supplyAsync() 运行异步计算（返回结果）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result of the asynchronous computation";
        });

        /*
            支持指定线程池，传递给方法执行
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result of the asynchronous computation";
        }, executorService);


        // Block and get the result of the Future
        System.out.println(future.get());

        System.out.println(future2.get());
    }

    /**
     * 使用 thenApply() 处理 CompletableFuture 的结果（可链式处理）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test4() throws ExecutionException, InterruptedException {

        // create a CompletableFuture
        CompletableFuture<String> helloWorldFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Planning";
        });

        // Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = helloWorldFuture
                .thenApply(name -> "Hello " + name)
                .thenApply(greeting -> greeting + ", Keep Learning!");

        // Block and get the result of the future
        System.out.println(greetingFuture.get());
    }


    /**
     * 组合两个 CompletableFuture
     * thenCombine() 组合两个独立的 future
     * thenCompose() 组合两个 出入参 关联的 Future；比如取得 A future 的值，传入 B future
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test5() throws ExecutionException, InterruptedException {
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 177.0;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combineFuture = weightFuture.thenCombine(heightFuture, (weight, height) -> {
            Double heightMeter = height / 100;
            return weight / (heightMeter * heightMeter);
        });

        System.out.println("Your BMI is - " + combineFuture.get());

    }

    /**
     * 使用 exceptionally() 回调处理异常
     * 如果发送异常，thenApply 不会继续执行
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test6() throws ExecutionException, InterruptedException {
        int age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).thenApply((result) -> {
            return "Hello " + result;
        }).thenApply(result -> {
            return result + ", Hurry up!";
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    /**
     * 使用 handle() 方法处理异常
     * 无论是否发生异常，handle 都会执行
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test7() throws ExecutionException, InterruptedException {
        int age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            if (ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

}