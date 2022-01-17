package com.planning.io.utils;

import java.io.File;
import java.util.Scanner;

/**
 * 定义一个工具类，该类要求用户运行该程序时输入一个路径。该工具类会将该路径下（及其子目录下）的所有文件列举出来
 * @Author: planning
 * @Date: 2019/4/15 20:43
 */
public class ExpandFileUtils {

    public static void main(String[] args) {
        // First: please input the file path
        Scanner sc = new Scanner(System.in);
        System.out.println("please input the file path....");
        String filePath = sc.next();

        // Second: list the file path, and iterator it's sub direction
        File file = new File(filePath);
        for(File f : file.listFiles()){
            // F:\MyStudyFile\IT-Books\书籍仓库\Java书籍类
            System.out.println(f.getPath());
            iteratorFilePath(f);
        }
    }

    /**
     * iterator the file path
     * @param file filePath
     */
    private static void iteratorFilePath(File file){
        while(file.isDirectory()){
            for(File f : file.listFiles()){
                System.out.println(f.getPath());
                iteratorFilePath(f);
            }
            break;
        }
    }
}