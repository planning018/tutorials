package com.planning.concurrent.generate;

/**
 * @Author: planning
 * @Date: 2018/12/17 17:11
 */
public class RunThreadByExtends extends Thread{

    @Override
    public void run() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        RunThreadByExtends one = new RunThreadByExtends();
        one.start();
    }

}