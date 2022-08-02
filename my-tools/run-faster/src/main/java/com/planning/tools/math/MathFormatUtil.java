package com.planning.tools.math;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author yxc
 * @date 2021/4/13 14:01
 */
public class MathFormatUtil {

    /**
     * 设置数字格式，保留有效小数位数为fractions
     *
     * @param fractions 保留有效小数位数
     * @return 数字格式
     */
    public static DecimalFormat decimalFormat(int fractions) {
        DecimalFormat df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(fractions);
        df.setMaximumFractionDigits(fractions);
        return df;
    }

    /**
     * Double 数值转换为指定的 String 格式
     */
    public static void formatDouble(){
        System.out.println(String.format("%.2f", 123.456));
    }

    public static void main(String[] args) {
        formatDouble();
    }
}
