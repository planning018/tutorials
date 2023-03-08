package com.planning.algorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 给定一个数组和目标和，从数组中找两个数字相加等于目标和，输出这两个数字的下标
 * <p>
 * Given nums = [2,7,11,15], Target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9
 * return [0,1]
 *
 * @author yxc
 * @date 2021/4/22 16:39
 */
public class A04_TwoSum {

    /**
     * 时间复杂度 O(n^2)，效率较低
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        // 双层 for 循环，暴力破解
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

    private int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];

        Map<Integer,Integer> mapValues = Maps.newHashMap();

        for(int i = 0; i < nums.length; i ++){

        }



        return result;
    }


    public static void main(String[] args) {
        A04_TwoSum testClass = new A04_TwoSum();
        int[] nums = {3,2,4};

        System.out.println(JSON.toJSONString(testClass.twoSum(nums, 6)));
    }
}
