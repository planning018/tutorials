package com.planning.algorithm;

import java.util.Objects;

/**
 * @author yxc
 * @date 2021/5/25 3:53 下午
 */
public class A08_LongestCommonPrefix {

    /*
     * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
     * 示例 1：
        输入：strs = ["flower","flow","flight"]
        输出："fl"
     *
     * 示例 2：
        输入：strs = ["dog","racecar","car"]
        输出：""
        解释：输入不存在公共前缀。
     */

    public String longestCommonPrefix(String[] strs) {
        if(Objects.isNull(strs) || strs.length == 0){
            return "";
        }

        String str = strs[0];
        int length = strs.length;
        for (int i = 1; i < length; i++) {
            str = longestCommonPrefix(str, strs[i]);
            if(str.length() == 0){
                break;
            }
        }

        return str;
    }

    private String longestCommonPrefix(String str, String str1) {
        int length = Math.min(str.length(), str1.length());
        int index = 0;
        while(index < length && str.charAt(index) == str1.charAt(index)){
            index++;
        }
        return str.substring(0, index);
    }
}
