package com.planning.algorithm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author yxc
 * @date 2021/5/20 7:45 上午
 */
public class A05_ReverseInteger {

    /*
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     *
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     *
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * 输入：x = 123 输出：321
     * 输入：x = -123 输出：-321
     * 输入：x = 120 输出：21
     * 输入：x = 0 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-integer
     */

    public int reverse(int x) {
        int rev = 0;

        while(x != 0){
            if(rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10){
                return 0;
            }
            int digtal =  x % 10;
            x  = x / 10;
            rev = rev * 10 + digtal;
        }
        return rev;
    }

    @Test
    public void test(){
        assertEquals(321, reverse(123));
    }

}
