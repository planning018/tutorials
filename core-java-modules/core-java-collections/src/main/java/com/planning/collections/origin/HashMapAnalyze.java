package com.planning.collections.origin;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap 源码分析
 * @Author: planning
 * @Date: 2019/7/23 19:13
 */
public class HashMapAnalyze {

    private final int MAXIMUM_CAPACITY = 1 << 30;

    @Test
    public void test(){
        // 了解一下 源码 的 tableSizeFor 方法
        //System.out.println(tableSizeFor(33));

        // 了解一下 HashMap#put() 方法的内部逻辑
        Map<String,String> map = new HashMap<>();
        map.put("person","zhangsan");

        System.out.println(JSON.toJSONString(map));
    }

    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}