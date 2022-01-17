package com.planning.io.utils;

import java.io.File;
import java.util.Scanner;

/**
 * 定义一个工具类，该类要求用户运行该程序时输入一个路径。该工具类会将该路径下的文件、文件夹的数量统计出来
 * @Author: planning
 * @Date: 2019/4/15 20:43
 */
public class ExpandFileUtils2 {

    public static Integer fileNum = 0;
    public static Integer dirNum = 0;

    public static void main(String[] args) {
        // First: please input the file path
        Scanner sc = new Scanner(System.in);
        System.out.println("please input the file path and we will count the file number....");
        String filePath = sc.next();

        // Second: list the file path, and count the file number
        File file = new File(filePath);
        countFileNumber(file);

    }

    /**
     * iterator the file path
     * @param file filePath
     */
    public static void countFileNumber(File file){
/*        fileNumber += file.list().length;
        while(file.isDirectory()){
            countFileNumber(file);
            break;
        }*/
        //todo 统计文件数量


        System.out.println("file number count is : " + fileNum);
    }
}