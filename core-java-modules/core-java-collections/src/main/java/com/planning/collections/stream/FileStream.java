package com.planning.collections.stream;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yxc
 * @since 2020-10-10 16:01
 **/
public class FileStream {

    static String filePath = "src/main/resources/bookInput.txt";
    static String csvPath = "src/main/resources/cakes.csv";
    static String folderPath = "src/main/resources/path";
    static String jarFilePath = "src/main/resources/testJar.jar";


    /**
     * 读取文件内容 - Files.lines
     * @throws IOException
     */
    @Test
    public void readLineByLineUsingFiles() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(System.out::println);
        }
    }

    /**
     * 读取文件内容 - Files.newBufferedReader
     * 并发读取文件内容 - parallel()
     * @throws IOException
     */
    @Test
    public void readLineByLineUsingBufferedReader() throws IOException {
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(filePath)).lines()) {
            lines.forEach(System.out::println);
        }

        System.out.println("---------------------------------------------------------");

        // it is advisable to go for proper performance benchmarking before considering parallel streams
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(filePath)).lines().parallel()) {
            lines.forEach(System.out::println);
        }
    }

    /**
     * 读取文件内容 - Files.readAllLines
     * @throws IOException
     */
    @Test
    public void readAllLinesUsingFiles() throws IOException {
        Stream<String> lines = Files.readAllLines(Paths.get(filePath)).stream();
        lines.forEach(System.out::println);
    }

    /**
     * 指定编码读取
     * @throws IOException
     */
    @Test
    public void readUtfEncodedFile() throws IOException {
        try (final Stream<String> lines = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8).lines()) {
            lines.forEach(System.out::println);
        }
    }

    /**
     * 计算文件指定单词数量
     * @throws IOException
     */
    @Test
    public void readFilterCountFromFile() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            long count = lines.filter(line -> line.startsWith("A")).count();
            System.out.println("The count line start with 'A' is " + count);
        }
    }

    /**
     * 输出文本所有单词（去重）
     * @throws IOException
     */
    @Test
    public void splitWordsFromFile() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            Set<String> wordSet = lines.flatMap(line -> Stream.of(line.split("\\W+"))).collect(Collectors.toSet());
            System.out.println(JSON.toJSONString(wordSet));
        }
    }

    private static Pattern pattern = Pattern.compile(",");

    /**
     * 读取 csv 文件 至 pojo 对象
     * @throws IOException
     */
    @Test
    public void loadItemFromCsvFile() throws IOException {
        try(final Stream<String> lines = Files.lines(Paths.get(csvPath))){
            List<Cake> cakeList = lines.skip(1).map(line -> {
                String[] arr = pattern.split(line);
                return new Cake(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]));
            }).collect(Collectors.toList());
            cakeList.forEach(System.out::println);
        }
    }

    /**
     * 列举文件目录
     * @throws IOException
     */
    @Test
    public void listDirectories() throws IOException {
        try(Stream<Path> folders = Files.list(Paths.get(folderPath))){
            folders.filter(Files::isDirectory).forEach(System.out::println);
        }
    }

    /**
     * 列举文件
     * @throws IOException
     */
    @Test
    public void walkFilesRecursively() throws IOException {
        try(Stream<Path> folders = Files.walk(Paths.get(folderPath))){
            folders.filter(Files::isRegularFile).forEach(System.out::println);
        }
    }

    /**
     * 查找文件
     * @throws IOException
     */
    @Test
    public void findFiles() throws IOException {
        int depth = Integer.MAX_VALUE;
        Stream<Path> paths = Files.find(Paths.get(folderPath), depth, (path, attr) -> attr.isRegularFile() && path.toString().endsWith(".txt"));
        paths.forEach(System.out::println);
    }

    @Test
    public void printJarFiles() throws IOException {
        try(JarFile jarFile = new JarFile(jarFilePath)){
            jarFile.stream().forEach(System.out::println);
        }
    }

    @Test
    public void printMatchJarFiles() throws IOException {
        try(JarFile jarFile = new JarFile(jarFilePath)){
            jarFile.stream().filter(file -> file.getName().contains("Ab")).forEach(System.out::println);
        }
    }

    @Data
    @AllArgsConstructor
    private class Cake {

        private int id;
        private String name;
        private int price;
    }

}