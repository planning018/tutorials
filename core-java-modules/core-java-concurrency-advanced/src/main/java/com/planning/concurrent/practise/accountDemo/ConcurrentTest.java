package com.planning.concurrent.practise.accountDemo;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Concurrent Test Class
 * @author yxc
 */
public class ConcurrentTest {

    private static Integer count = 0;

    private void addNum() {
        synchronized (this){
            count++;

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ">>> count: " + count + " time: " + new Date());
        }
    }

    @Test
    public void cal() {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            executorService.submit(this::addNum);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end >>> " + count);

    }

}
