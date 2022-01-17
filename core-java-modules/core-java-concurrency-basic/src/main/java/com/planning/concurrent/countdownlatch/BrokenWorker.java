package com.planning.concurrent.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author yxc
 * @date 2021/1/13 19:50
 */
public class BrokenWorker implements Runnable {

    private final List<String> outputScaper;
    private final CountDownLatch countDownLatch;

    BrokenWorker(final List<String> outputScaper, final CountDownLatch countDownLatch){
        this.outputScaper = outputScaper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        if(true){
            throw new RuntimeException("Oh dear");
        }

        countDownLatch.countDown();
        outputScaper.add("Counted down");
    }
}
