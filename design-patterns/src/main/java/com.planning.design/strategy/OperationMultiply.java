package com.planning.design.strategy;

/**
 * @author planning
 * @create 2019-10-28 21:07
 **/
public class OperationMultiply implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return Math.multiplyExact(num1, num2);
    }
}