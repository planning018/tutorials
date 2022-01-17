package com.planning.collections.stream;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Stream toMap Api Study
 * @author planning
 * @create 2019-10-28 19:18
 **/
public class StreamToMapStudy {

    private List<Book> bookList = new ArrayList<>();

    {
        bookList.add(new Book("Thinking in java",2000, 134.0));
        bookList.add(new Book("Design patterns",1990, 112.0));
        bookList.add(new Book("Code Clean",2001, 94.0));
    }

    @Data
    class Book {
        private String bookName;
        private Integer releaseYear;
        private Double price;

        public Book(String bookName, Integer releaseYear, Double price){
            this.bookName = bookName;
            this.releaseYear = releaseYear;
            this.price = price;
        }
    }

    @Test
    public void test01(){
        // toMap operator
        Map<String, Double> bookMap = bookList.stream().collect(Collectors.toMap(Book::getBookName, Book::getPrice));
        System.out.println(JSON.toJSONString(bookMap));
    }

    @Test
    public void test02(){
        // if key is repeated
        bookList.add(new Book("Code Clean",2002, 104.0));
        // if choose test01 ---> java.lang.IllegalStateException: Duplicate key 94.0
        Map<String, Double> bookMap = bookList.stream().collect(Collectors.toMap(Book::getBookName, Book::getPrice,
                (existing,replace) -> replace));
        System.out.println(JSON.toJSONString(bookMap));
    }

    @Test
    public void test03(){
        // return a concurrentHashMap; default is hashMap
        ConcurrentHashMap<String, Double> concurrentHashMap = bookList.stream().collect(Collectors.toMap(Book::getBookName, Book::getPrice,
                (existing, replace) -> replace, ConcurrentHashMap::new));
        System.out.println(JSON.toJSONString(concurrentHashMap));
    }

    @Test
    public void test04(){
        TreeMap<String, Double> treeMap = bookList.stream().sorted(Comparator.comparing(Book::getBookName)).collect(Collectors.toMap(Book::getBookName, Book::getPrice,
                (existing, replace) -> replace, TreeMap::new));
        System.out.println(JSON.toJSONString(treeMap));
    }
}