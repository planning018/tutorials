package com.planning.algorithm;

import org.junit.Test;

/**
 * @author yxc
 * @date 2021/5/21 6:25 下午
 */
public class A06_PalindromeNumber {

    /*
     *  给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     *  回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     *
     *  示例 1： 输入：x = 121  输出：true
     *  输入：x = -121  输出：false   解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     *
     */

    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }

        String xStr = String.valueOf(x);
        StringBuilder sbr = new StringBuilder();
        for (int i = xStr.length() - 1; i < xStr.length(); i--) {
            sbr.append(xStr.charAt(i));
            if(i == 0){
                break;
            }
        }
        int result = Integer.parseInt(sbr.toString().substring(0, sbr.length()-1));
        if(result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10){
            return false;
        }

        return x == result;
    }

    public boolean isPalindrome1(int x){
        if(x < 0 || (x % 10 == 0 && x != 0)){
            return false;
        }

        int reverseNumber = 0;
        while(x > reverseNumber){
            reverseNumber = reverseNumber * 10 + x % 10;
            x = x / 10;
        }

        return x == reverseNumber || x == reverseNumber / 10;

    }

    @Test
    public void test(){
        System.out.println(isPalindrome1(1234567899));
    }
}
