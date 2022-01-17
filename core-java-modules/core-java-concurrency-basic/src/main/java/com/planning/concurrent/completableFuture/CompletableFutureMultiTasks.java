package com.planning.concurrent.completableFuture;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yxc
 * @date 2021/3/10 19:36
 */
public class CompletableFutureMultiTasks {

    @Test
    public void testCompletableFutureAndExecutorService(){
        List<Long> numList = Lists.newArrayList();

        // 1. 定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 2. 多个 CompletableFuture 并行
        List<CompletableFuture<Void>> completableFutures = Lists.newArrayList();
        List<Integer> nums = Lists.newArrayList(1,2,3);
        nums.forEach(i -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                numList.add(System.currentTimeMillis());
            }, executorService);

            completableFutures.add(future);
        });

        // 3. 验证结果
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).thenRunAsync(() -> {
            System.out.println("end counting...");
        }).join();

        System.out.println("result is " + JSON.toJSONString(numList));
    }

}
