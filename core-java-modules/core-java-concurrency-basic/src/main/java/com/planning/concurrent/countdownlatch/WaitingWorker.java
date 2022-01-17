package com.planning.concurrent.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author yxc
 * @date 2021/1/13 20:19
 */
public class WaitingWorker implements Runnable{

    private final List<String> outputScraper;
    private final CountDownLatch readyThreadCounter;
    private final CountDownLatch callingThreadCounter;
    private final CountDownLatch completedThreadCounter;

    WaitingWorker(final List<String> outputScraper, final CountDownLatch readyThreadCounter,
                  final CountDownLatch callingThreadCounter, final CountDownLatch completedThreadCounter){
        this.outputScraper = outputScraper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadCounter = callingThreadCounter;
        this.completedThreadCounter = completedThreadCounter;
    }

    @Override
    public void run() {
        // mark this thread as read / started
        readyThreadCounter.countDown();

        try {
            callingThreadCounter.await();
            outputScraper.add("Counted down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            completedThreadCounter.countDown();
        }
    }
}
