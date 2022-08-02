package com.planning.tools.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author yxc
 * @date 2021/1/18 11:07
 */
public class BigDecimalTools {

    public static void main(String[] args) {
        descMathPrecision();
        calculateBasicOpt();
    }

    /**
     * BigDecimal 精度问题
     */
    private static void descMathPrecision() {
        Double right = 8.2;
        // 存在精度问题
        System.out.println((int) (right.doubleValue() * 100));

        // 解决精度问题
        System.out.println(BigDecimal.valueOf(right).multiply(new BigDecimal(100)).intValue());
        System.out.println(new BigDecimal(String.valueOf(right)).multiply(new BigDecimal(100)).intValue());

        // 测试基本预算
        System.out.println(Math.floorDiv(1L, 3L));
    }

    /**
     * BigDecimal 相除
     *      - 四舍五入
     */
    private static void calculateBasicOpt(){
        BigDecimal num1 = new BigDecimal(3);
        BigDecimal num2 = new BigDecimal(7);

        // 四舍五入
        System.out.println(num1.divide(num2, 1, RoundingMode.HALF_UP).doubleValue());
    }
}
