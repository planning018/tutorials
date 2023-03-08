package com.planning.jvm.lambda;

import org.junit.Test;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 举例说明 lambda 表达式的优势
 *
 * @Author: planning
 * @Date: 2019/3/2 17:01
 */
public class LambdaExamples {

    /**
     * 求平方和
     */
    @Test
    public void printSquare() {
        Integer[] num = {1, 2, 3, 4, 5};
        int sum = 0;

        // 传统 forEach 的做法
        for (int i : num) {
            sum += Math.pow(i, 2);
        }

        // 使用 lambda 表达式
        List<Integer> numList = Arrays.asList(num);
        //分别打印出每一个数字的平方
        // numList.stream().map((x) -> x*x).forEach(System.out::println);
        int sum2 = numList.stream().map((x) -> x * x).reduce((x, y) -> x + y).get();

        System.out.println("使用 forEach 总和是：" + sum);
        System.out.println("总和 lambda 表达式总和是：" + sum2);
    }

    /**
     * 使用 lambda 表达式遍历集合（推荐）
     */
    @Test
    public void iteratorMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        map.forEach((k, v) -> {
            System.out.println(k + " --- " + v);
        });
    }

    /**
     * 使用未既成事实的 final 变量，会导致无法通过编译
     */
    public void testFinalVar(){
        String name = "Hello" + "world";
        name = "Lambda";

        List<String> tempList = Arrays.asList("1","2","3");

        // 使用未既成事实的 final 变量，会导致无法通过编译
        // tempList.forEach(num -> num + name);
    }

    @Test
    public void testTL(){
        ThreadLocal<DateFormatter> initial = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("yyyy-MM-dd")));
    }

    @Test
    public void testListNull(){
        List<Integer> list = Arrays.asList(1,2,3,4,null);
        list.forEach(num -> System.out.println(num.equals(3)));
    }
}