package com.planning.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * 删除排序数组中的重复项
 *
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 举例：
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * @author yxc
 * @since 2020-10-10 14:19
 **/
public class A01_removeDup {

    public int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int i = 0;
        for(int j = 1; j < nums.length; j++){
            if(nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    @Test
    public void test(){
        int[] intArray = {1,1,2};
        System.out.println(removeDuplicates(intArray));
        System.out.println(JSON.toJSONString(intArray));
    }
}