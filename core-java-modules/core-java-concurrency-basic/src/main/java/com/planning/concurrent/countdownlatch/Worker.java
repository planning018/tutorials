package com.planning.concurrent.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 详细使用见 {@link com.planning.thread.basic.countdownlatch.CountdownLatchTest}
 *
 * @author yxc
 * @date 2021/1/13 20:07
 */
public class Worker implements Runnable {

    private final List<String> outputScraper;
    private final CountDownLatch countDownLatch;

    Worker(final List<String> outputScraper, final CountDownLatch countDownLatch){
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        // Do some work
        System.out.println("Doing some logic");
        outputScraper.add("Counted down");
        countDownLatch.countDown();
    }
}
