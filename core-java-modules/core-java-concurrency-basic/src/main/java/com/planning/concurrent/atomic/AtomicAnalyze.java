package com.planning.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: planning
 * @Date: 2019/7/31 23:47
 */
public class AtomicAnalyze {

    private AtomicInteger count = new AtomicInteger(0);
    public int next(){
        while(true){
            int current = count.get();
            int next = current + 1;
            if(count.compareAndSet(current,next)){
                return next;
            }
        }
    }
}