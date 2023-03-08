package com.planning.design.strategy;

/**
 * @author planning
 * @create 2019-10-28 21:05
 **/
public class OperatorSubtraction implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return Math.subtractExact(num1, num2);
    }
}