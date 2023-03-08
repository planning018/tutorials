package com.planning.algorithm;

import org.junit.Test;

/**
 * 买卖股票的最佳时机
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 举例：
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * @author yxc
 * @since 2020-10-13 9:42
 **/
public class A02_maxProfit {

    public int maxProfit1(int[] prices) {
        int i = 0;
        int peek = prices[0];
        int vally = prices[0];

        int maxProfit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            vally = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peek = prices[i];
            maxProfit += peek - vally;
        }
        return maxProfit;
    }

    /**
     * 最简单的一次遍历
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    @Test
    public void testResult() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit2(prices));
        System.out.println(maxProfit1(prices));
    }
}