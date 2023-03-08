package com.planning.design.strategy;

/**
 * @author planning
 * @create 2019-10-28 21:14
 **/
public class StrategyPatternDemo {

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperatorSubtraction());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));


        // 从这个角度看，策略模式完全可以通过下面的方式执行，需要思考原因？
/*        List<Strategy> strategies = new ArrayList<>();
        strategies.add(new OperationAdd());
        strategies.add(new OperatorSubtraction());
        strategies.add(new OperationMultiply());

        for (Strategy strategy : strategies) {
            int result = strategy.doOperation(10,5);
            System.out.println("result is : " + result);
        }*/
    }
}