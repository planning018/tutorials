package com.planning.concurrent.completableFuture;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author yxc
 * @date 2021/3/10 19:38
 */
public class CompletableFutureTimeout {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    /**
     * CompletableFuture timeout
     */
    @Test
    public void testSupplyAsyncAndTimeOut() {
        // 准备超时组件
        final CompletableFuture<Object> oneSecondTimeout = failAfter(Duration.ofMillis(500L)).exceptionally(xxx -> "timeout exception");

        // 开始执行任务，并汇总结果
        List<Long> nums = Lists.newArrayList(1L, 2L, 3L);
        List<Object> collect = nums.stream().map(l -> CompletableFuture.anyOf(createTaskSupplier(l), oneSecondTimeout))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        // 验证结果
        List<Long> result = Lists.newArrayList();
        for (Object obj : collect) {
            if (!(obj instanceof List)) {
                continue;
            }

            result.addAll((List<Long>) obj);
        }

        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testSupplyAsyncAndTimeOut2(){

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<CompletableFuture<Void>> completableFutures = Lists.newArrayList();

        List<Long> result = Lists.newArrayList();
        List<Long> nums = Lists.newArrayList(1L, 2L, 3L);
        nums.forEach(l -> {
            // 这种方式也能实现中断，不过任务是串行执行的，没有上一种方法好
            CompletableFuture.supplyAsync(() -> computeNum(l), executorService)
                    .acceptEither(timeoutAfter(400L), result::addAll)
                    .join();

            /*
             * 这种方式会随机丢失部分结果
             */
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> computeNum(l), executorService)
                    .acceptEither(timeoutAfter(500L), result::addAll);
            completableFutures.add(future);
        });

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();

        System.out.println(JSON.toJSONString(result));
    }

    /**
     * 业务 future
     *
     * @param l
     * @return
     */
    private CompletableFuture<List<Long>> createTaskSupplier(Long l) {
        return CompletableFuture.supplyAsync(() -> computeNum(l))
                .exceptionally(xx -> null);
    }

    /**
     * 超时组件实现
     *
     * @param duration
     * @param <T>
     * @return
     */
    private static <T> CompletableFuture<T> failAfter(Duration duration) {
        final CompletableFuture<T> promise = new CompletableFuture<>();
        scheduler.schedule(() -> {
            final TimeoutException ex = new TimeoutException("Timeout after " + duration);
            return promise.completeExceptionally(ex);
        }, duration.toMillis(), TimeUnit.MILLISECONDS);
        return promise;
    }

    /**
     * 业务方法
     *
     * @param l
     * @return
     */
    private List<Long> computeNum(Long l) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList(RandomUtils.nextLong(), l);
    }

    @Test
    public void testCompletableFutureWithAcceptEither(){
        CompletableFuture.supplyAsync(() -> computeNum(RandomUtils.nextLong()))
                .acceptEither(timeoutAfter(200L), result -> System.out.println("compute task result: " + JSON.toJSONString(result)))
                .exceptionally(ex -> null)
                .join();
    }

    private <T> CompletableFuture<T> timeoutAfter(Long timeout) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        scheduler.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, TimeUnit.MILLISECONDS);
        return result;
    }
}
