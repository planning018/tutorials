package com.planning.collections.origin;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 分析 ArrayList 源码 --- JDK 1.8
 * @Author: planning
 * @Date: 2019/7/12 15:19
 */
public class ArrayListAnalyze {

    /**
     * 使用无参构造器，则使用数组 Object[] elementData 接收参数，默认大小是 10
     */
    @Test
    public void noParamConstructor(){
        List<String> strList = new ArrayList<>();
        strList.add("I");
        strList.add("want");
        strList.add("to");
        strList.add("know");
        strList.add("the");
        strList.add("truth");

        System.out.println(JSON.toJSONString(strList));
    }

    /**
     *  在使用 ArrayList(Collection<? extends E> c) 构造方法时，为什么 c.toArray 有可能不是 Object[] 类型
     */
    @Test
    public void test(){
        Father[] fathers = new Son[]{};
        // class [Lcom.planning.origin.ArrayListAnalyze$Son;
        System.out.println(fathers.getClass());

        List<String> strList = new MyList();
        // class [Ljava.lang.String;
        System.out.println(strList.toArray().getClass());
    }

    class Father{}

    class Son extends Father {}

    class MyList extends ArrayList<String> {

        /**
         * 子类重写父类的方法，返回值可以不一样
         *
         * @return
         */
        @Override
        public Object[] toArray() {
            // 为了方便，直接把值写死
            return new String[]{"1","2","3"};
        }
    }
}