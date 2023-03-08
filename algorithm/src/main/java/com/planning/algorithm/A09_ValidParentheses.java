package com.planning.algorithm;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yxc
 * @date 2021/5/26 7:10 下午
 */
public class A09_ValidParentheses {

    /*
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
           有效字符串需满足：
           左括号必须用相同类型的右括号闭合。
           左括号必须以正确的顺序闭合。
     */
    public boolean isValid(String s) {
        if(s.length() % 2 == 1){
            return false;
        }

        Map<Character,Character> pairs = new HashMap<Character,Character>(){{
           put(')', '(' );
           put('}', '{' );
           put(']', '[' );
        }};

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if(pairs.containsKey(s.charAt(i))){
                if(stack.isEmpty() || stack.peek() != pairs.get(s.charAt(i))){
                    return false;
                }
                stack.pop();
            }else {
                stack.push(s.charAt(i));
            }
        }

        return stack.isEmpty();
    }




}
