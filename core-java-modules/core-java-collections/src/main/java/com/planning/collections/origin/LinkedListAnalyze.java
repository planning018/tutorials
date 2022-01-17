package com.planning.collections.origin;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 分析 LinkedList 源码
 * @Author: planning
 * @Date: 2019/7/15 10:52
 */
public class LinkedListAnalyze {

    @Test
    public void test(){
        List<String> strList = new LinkedList<>();
        strList.add("hello");
        strList.add("java");
        strList.add("I");
        strList.add("learn");
        strList.add("LinkedList");
        //System.out.println(JSON.toJSONString(strList));

        // 分析一下 LinkedList 的 Node<E> node(int index)
        //strList.add(4,"java-linked");
        //System.out.println(JSON.toJSONString(strList));

        // remove(int index) 方法
        String removeValue = strList.remove(3);
        System.out.println(removeValue);

    }


}