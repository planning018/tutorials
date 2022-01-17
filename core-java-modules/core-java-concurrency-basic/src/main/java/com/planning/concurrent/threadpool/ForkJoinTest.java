package com.planning.concurrent.threadpool;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin demo
 * @author planning
 * @since 2019-10-30 16:16
 **/
public class ForkJoinTest {

    private double[] d = new double[10001];

    private class ForkJoinTask extends RecursiveTask<Integer>{
        private int first;
        private int last;
        public ForkJoinTask(int first,int last){
            this.first = first;
            this.last = last;
        }

        @Override
        protected Integer compute() {
            int subCount;
            if(last - first < 10){
                subCount = 0;
                for (int i = first; i < last; i ++){
                    if(d[i] < 0.5){
                        subCount ++;
                    }
                }
            }else {
                int mid = (first + last) >>> 1;
                ForkJoinTask left = new ForkJoinTask(first,mid);
                left.fork();
                ForkJoinTask right = new ForkJoinTask(mid + 1, last);
                right.fork();
                subCount = left.join();
                subCount += right.join();
            }
            return subCount;
        }
    }


    @Test
    public void test(){
        d = ThreadUtils.createArrayOfRandomDoubles();
        Integer result = new ForkJoinPool().invoke(new ForkJoinTask(0, 10000));
        System.out.println("Found " + result + " values");
    }

}