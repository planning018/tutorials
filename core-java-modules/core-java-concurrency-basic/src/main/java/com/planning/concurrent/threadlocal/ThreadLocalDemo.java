package com.planning.concurrent.threadlocal;

/**
 * 分析 ThreadLocal 的用法
 * @Author: planning
 * @Date: 2019/7/30 9:57
 */
public class ThreadLocalDemo {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>(){
        // 实现 initialValue
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    private int nextSeq(){
        seqCount.set(seqCount.get() + 1);

        return seqCount.get();
    }

    public static void main(String[] args) {
        ThreadLocalDemo localDemo = new ThreadLocalDemo();

        SeqThread thread1 = new SeqThread(localDemo);
        SeqThread thread2 = new SeqThread(localDemo);
        SeqThread thread3 = new SeqThread(localDemo);
        SeqThread thread4 = new SeqThread(localDemo);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }


    private static class SeqThread extends Thread {
        private ThreadLocalDemo threadLocalDemo;

        private SeqThread(ThreadLocalDemo threadLocalDemo){
            this.threadLocalDemo = threadLocalDemo;
        }

        @Override
        public void run() {
            for(int i = 0; i < 3; i++){
                System.out.println(Thread.currentThread().getName() + " seqCount :" + threadLocalDemo.nextSeq());
            }
        }
    }

}