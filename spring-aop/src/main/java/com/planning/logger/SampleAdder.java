package com.planning.logger;

/**
 * @author yxc
 * @date 2021/4/16 14:30
 */
public class SampleAdder {

    public int add(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Make sure all the arguments are greater than zero.");
        }
        return a + b;
    }
}
