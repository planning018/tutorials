package com.planning.concurrent.countdownlatch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yxc
 * @date 2021/1/13 20:31
 */
public class CountdownLatchTest {

    @Test
    public void whenParallelProcessing_thenMainThreadWillBlockUntilCompletion() throws InterruptedException {
        // Given
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(outputScraper, countDownLatch))).limit(5).collect(Collectors.toList());

        // When
        workers.forEach(Thread::start);
        countDownLatch.await();
        outputScraper.add("Latch released");

        // Then
        assertThat(outputScraper).containsExactly("Counted down","Counted down","Counted down","Counted down","Counted down","Latch released");
    }

    @Test
    public void whenFailingToParallelProcess_thenMainThreadShouldTimeout() throws InterruptedException {
        // Given
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new BrokenWorker(outputScraper, countDownLatch))).limit(5).collect(Collectors.toList());

        // When
        workers.forEach(Thread::start);
        final boolean result = countDownLatch.await(3L, TimeUnit.SECONDS);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void whenDoingLotsOfThreadsInParallel_thenStartThemAtTheSameTime() throws InterruptedException {
        // Given
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadCounter = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new WaitingWorker(outputScraper, readyThreadCounter, callingThreadCounter, completedThreadCounter)))
                .limit(5).collect(Collectors.toList());

        // When
        workers.forEach(Thread::start);
        // Block until workers start
        readyThreadCounter.await();
        outputScraper.add("Workers ready");
        // Start workers
        callingThreadCounter.countDown();
        // Block until workers finish
        completedThreadCounter.await();
        outputScraper.add("Workers complete");

        // Then
        assertThat(outputScraper).containsExactly("Workers ready","Counted down", "Counted down", "Counted down", "Counted down", "Counted down", "Workers complete");
    }


}
