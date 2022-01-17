package com.planning.concurrent.reentrantlock;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yxc
 * @date 2021/3/16 16:45
 */
@Slf4j
public class SynchronizedHashMapWithRwLock {

    private static Map<String, String> syncHashMap = Maps.newHashMap();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void put(String key, String value) {
        writeLock.lock();
        try {
            log.info(Thread.currentThread().getName() + " writing");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            syncHashMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(String key) {
        readLock.lock();
        try {
            log.info(Thread.currentThread().getName() + " reading");
            return syncHashMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public String remove(String key) {
        writeLock.lock();
        try {
            return syncHashMap.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public boolean containsKey(String key) {
        readLock.lock();
        try {
            return syncHashMap.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

    boolean isReadLockAvailable() {
        return readLock.tryLock();
    }

    public static void main(String[] args) {
        final int threadCount = 3;
        final ExecutorService service = Executors.newFixedThreadPool(threadCount);

        SynchronizedHashMapWithRwLock object = new SynchronizedHashMapWithRwLock();

        service.execute(new Thread(new Writer(object), "Writer"));
        service.execute(new Thread(new Reader(object), "Reader1"));
        service.execute(new Thread(new Reader(object), "Reader2"));

        service.shutdown();
    }

    private static class Reader implements Runnable {

        SynchronizedHashMapWithRwLock object;

        public Reader(SynchronizedHashMapWithRwLock object) {
            this.object = object;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                object.get("key" + i);
            }
        }
    }

    private static class Writer implements Runnable {

        SynchronizedHashMapWithRwLock object;

        public Writer(SynchronizedHashMapWithRwLock object) {
            this.object = object;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                object.put("key" + i, "value" + i);
            }
        }
    }
}
