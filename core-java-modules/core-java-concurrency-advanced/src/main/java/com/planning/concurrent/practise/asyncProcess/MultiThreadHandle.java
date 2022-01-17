package com.planning.concurrent.practise.asyncProcess;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 学习 xiyt 写的小工具，多线程处理任务
 * @Author: planning
 * @Date: 2019/7/4 17:26
 */
public class MultiThreadHandle {

    public static void main(String[] args) {
        List<String> strs = Stream.of("a","b","c").collect(Collectors.toList());
        AsyncDataProcessor<String, String> asyncDataProcessor = new AsyncDataProcessor<>(strs);
        asyncDataProcessor.run(subData -> {
            subData.forEach(d -> System.out.println("position1 :" + d));
            return "处理完了" + subData.size();
        });

        // 等待所有线程处理完，拿到结果
        List<String> newResult = asyncDataProcessor.join().getResults();
        newResult.forEach(item -> {
            System.out.println("position2 :" + item);
        });
    }
}