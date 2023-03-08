package com.planning.jvm.lambda;

/**
 * lambda 表达式学习
 * @Author: planning
 * @Date: 2019/3/2 15:54
 */
public class HelloLambda {

    public static void main(String[] args) {
        // 使用 lambda 表达式
        execute(() -> {
            System.out.println("hello work by lambda expression");
        });

        // 使用匿名内部类
        execute(new MyWorkerInterface() {
            @Override
            public void work() {
                System.out.println("hello work by Anonymous class");
            }
        });
    }

    private static void execute(MyWorkerInterface worker) {
        worker.work();
    }

}