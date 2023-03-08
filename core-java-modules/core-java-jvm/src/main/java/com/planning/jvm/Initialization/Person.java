package com.planning.jvm.Initialization;

/**
 * 回顾 初始化块
 * @Author: planning
 * @Date: 2019/4/3 10:16
 */
public class Person {
    static {
        System.out.println("这里是初始化块......");
        int a = 4;
        if( a > 3){
            System.out.println("这里的变量 a 局部变量");
        }
    }

    public Person(){
        System.out.println("这里是构造器方法");
    }

    public static void main(String[] args) {
        new Person();
    }
}