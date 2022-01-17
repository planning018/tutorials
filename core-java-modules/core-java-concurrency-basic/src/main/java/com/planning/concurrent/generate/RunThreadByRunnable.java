package com.planning.concurrent.generate;

/**
 * @Author: planning
 * @Date: 2018/12/17 17:11
 */
public class RunThreadByRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("hello");
    }


    public static void main(String[] args) {
        RunThreadByRunnable two = new RunThreadByRunnable();
        Thread thread = new Thread(two);
        thread.start();
    }
}