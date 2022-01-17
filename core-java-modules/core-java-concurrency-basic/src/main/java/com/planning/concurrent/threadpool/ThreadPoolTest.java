package com.planning.concurrent.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * compare ForkJoinPool
 *
 * @author planning
 * @since 2019-10-30 16:57
 **/
@Slf4j
public class ThreadPoolTest {

    private double[] d = new double[10001];

    /**
     * 定时调度器: 把数据归档到文件中
     */
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR = new ScheduledThreadPoolExecutor(4, new ThreadFactoryBuilder()
            .setNameFormat("planning-thread-%d").setDaemon(true).build());

    private void archiveDataToFile() {
        SCHEDULED_EXECUTOR.scheduleAtFixedRate(new ArchiveTask("/thread/demoData"), 60, 120, TimeUnit.SECONDS);
    }

    private void futureCount(){
        d = ThreadUtils.createArrayOfRandomDoubles();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, Long.MAX_VALUE, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setNameFormat("planning-thread-%d").setDaemon(true).build());
        Future[] futures = new Future[4];
        int size = d.length / 4;
        for (int i = 0; i < 3; i++) {
            futures[i] = executor.submit(new ThreadPoolExecutorTask(i * size, (i + 1) * size - 1));
        }
        futures[3] = executor.submit(new ThreadPoolExecutorTask(3 * size, d.length - 1));
        int n = 0;
        for (int i = 0; i < 4; i++) {
            try {
                n += (int) futures[i].get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Found " + n + " values");
    }

    private class ThreadPoolExecutorTask implements Callable<Integer> {
        private int first;
        private int last;

        public ThreadPoolExecutorTask(int first, int last) {
            this.first = first;
            this.last = last;
        }

        @Override
        public Integer call() {
            int subCount = 0;
            for (int i = first; i < last; i++) {
                if (d[i] < 0.5) {
                    subCount++;
                }
            }
            return subCount;
        }
    }

    /**
     * 归档任务
     */
    private class ArchiveTask implements Runnable {

        private String archivePath;

        public ArchiveTask(String archivePath) {
            this.archivePath = archivePath;
        }

        @Override
        public void run() {
            File file = new File(archivePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try (FileWriter writer = new FileWriter(file);) {
                String testJson = "test content";
                writer.write(testJson);
                writer.flush();
                log.info("archive config to file success");
            } catch (Exception e) {
                log.error("archive config to file error, ", e);
            }

        }
    }

    @Test
    public void junitTest(){
        // futureCount();

        archiveDataToFile();
    }

}