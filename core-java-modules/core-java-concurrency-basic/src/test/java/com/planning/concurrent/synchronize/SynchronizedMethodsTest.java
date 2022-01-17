package com.planning.concurrent.synchronize;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * synchronized 单元测试
 *
 * @author yxc
 * @date 2021/2/1 19:12
 */
public class SynchronizedMethodsTest {

    @Test
    public void givenMultiThread_whenNonSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        SynchronizedMethods method = new SynchronizedMethods();

        IntStream.range(0, 1000).forEach(count -> service.submit(method::calculate));

        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, method.getSum());
    }

    @Test
    public void givenMultiThread_whenSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        SynchronizedMethods method = new SynchronizedMethods();

        IntStream.range(0, 1000).forEach(count -> service.submit(method::synchronizedCalculate));

        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, method.getSynSum());
    }

    @Test
    public void givenMultiThread_whenStaticSyncMethod() throws InterruptedException {
        // ExecutorService service = Executors.newCachedThreadPool();
        ExecutorService service = Executors.newFixedThreadPool(3);

        IntStream.range(0, 1000).forEach(count -> service.submit(SynchronizedMethods::syncStaticCalculate));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, SynchronizedMethods.staticSum);
    }
}
