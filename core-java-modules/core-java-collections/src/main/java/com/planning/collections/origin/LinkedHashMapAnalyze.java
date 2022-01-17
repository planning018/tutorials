package com.planning.collections.origin;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: planning
 * @Date: 2019/7/31 16:27
 */
public class LinkedHashMapAnalyze {

    public static void main(String[] args) {
        // 创建一个只有 5 个元素的缓存
        LRU<Integer,Integer> lru = new LRU<>(5,0.75f);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.put(5, 5);
        lru.put(6, 6);
        lru.put(7, 7);

        System.out.println(lru.get(4));

        lru.put(6,666);
        // 返回 {3:3,5:5,7:7,4:4,6:666}
        System.out.println(JSON.toJSONString(lru));
    }


    static class LRU<K,V> extends LinkedHashMap{

        // 保存缓存的容量
        private int capacity;

        private LRU(int capacity, float loadFactor){
            super(capacity, loadFactor,true);
            this.capacity = capacity;
        }

        /**
         * 重写 removeEldestEntry()方法设置何时移除旧元素
         * @param eldest
         * @return
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            // 当元素个数大于了缓存的容量，就移除元素
            return size() > capacity;
        }
    }

}