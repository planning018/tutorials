package com.planning.design.singleton;

/**
 * 单例模式 demo 展示
 */
public class SingletonDemo {

    /**
     * 声明静态成员变量
     */
    private static SingletonDemo singletonDemo;

    /**
     * 私有化构造器
     */
    private SingletonDemo() {
    }

    /**
     * 提供静态工厂方法
     *
     * @return
     */
    private static SingletonDemo getInstance() {
        if (singletonDemo == null) {
            singletonDemo = new SingletonDemo();
        }

        return singletonDemo;
    }
}
