package com.planning.io.oio;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 读取 URL 指向的文件
 *
 * @author yxc
 * @date 2022/2/14 3:28 下午
 */
public class ReadFileFromDifferentType {

    public static void main(String[] args) throws Exception {
        // readFileOnline();
        readFileFromByteArray();
    }

    public static void readFileOnline() throws Exception {
        // 使用 apache commons 工具类
        File destFile = new File("model.pmml");
        URL url = new URL("http://dev-predict.amh-group.com/predict-fs/v2/file/download/predict-phantom/realtime-losingorder-xgb/v20220111.0/model.pmml");
        FileUtils.copyURLToFile(url, destFile);

        List<String> stringList = new BufferedReader(new InputStreamReader(new FileInputStream(destFile))).lines().collect(Collectors.toList());
        System.out.println(stringList.get(0));
    }

    public static void readFileFromByteArray() throws IOException {
        byte[] bytes = new byte[]{'H','e','l','l','o'};
        File file = new File("readBytes.txt");
        Files.write(file.toPath(), bytes);
    }
}
