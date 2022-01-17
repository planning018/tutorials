package com.planning.concurrent.synchronize;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;


/**
 * @author yxc
 * @date 2021/2/1 19:27
 */
public class SynchronizedBlocksTest {

    @Test
    public void givenMultiThread_whenBlockSync() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        SynchronizedBlocks block = new SynchronizedBlocks();

        IntStream.range(0, 1000).forEach(count -> service.submit(block::performSyncTask));
        service.awaitTermination(500, TimeUnit.MILLISECONDS);
        assertEquals(1000, block.getCount());
    }

    @Test
    public void givenMultiThread_whenBlockStaticSync() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);

        IntStream.range(0, 1000).forEach(count -> service.submit(SynchronizedBlocks::performStaticSyncTask));
        service.awaitTermination(500, TimeUnit.MILLISECONDS);
        assertEquals(1000, SynchronizedBlocks.getStaticCount());
    }

    @Test
    public void givenHoldingTheLock_whenReentrant_thenCanAcquireItAgain(){
        Object lock = new Object();
        synchronized (lock) {
            System.out.println("First time acquiring it");

            synchronized (lock){
                System.out.println("Entering again");

                    synchronized (lock) {
                        System.out.println("And again");
                    }
            }
        }
    }
}
