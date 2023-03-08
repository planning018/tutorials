package com.planning.design.strategy;

/**
 * @author planning
 * @create 2019-10-28 21:03
 **/
public class OperationAdd implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return Math.addExact(num1,num2);
    }
}