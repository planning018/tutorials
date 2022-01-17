package com.planning.collections;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yxc
 * @date 2021/4/14 14:21
 */
public class StreamFunctionTest {

    private void filterValue(List<Integer> numList){
        numList = numList.stream().filter(num -> num < 5).collect(Collectors.toList());
    }

    @Test
    public void testFilter(){
        List<Integer> numList = Lists.newArrayList(1,2,3,4,5,6);
        filterValue(numList);
        System.out.println(JSON.toJSONString(numList));
    }

    @Test
    public void testListsJoin(){
        List<Integer> startCityList = Lists.newArrayList(100000,100001,100002);
        List<Integer> endCityList = Lists.newArrayList(200000,200001,200002);

        String format = "%s_%s";

        // expect: 100000_200000,100000_200001,...,100002_200002
        String result = startCityList.stream().flatMap(start -> endCityList.stream().map(end -> String.format(format, start, end))).collect(Collectors.joining(","));
        System.out.println(result);
    }

}
