package com.planning.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

/**
 * 加载自定义路径下的 class 文件
 *
 * @author yxc
 * @date 2021/3/15 15:46
 */
public class PathClassLoader extends ClassLoader {

    public static final String PACKAGE_NAME = "com.planning.review";
    private String classPath;

    public PathClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (PACKAGE_NAME.startsWith(name)) {
            byte[] classData = getData(name);
            if (Objects.isNull(classData)) {
                throw new ClassNotFoundException();
            } else {
                return defineClass(name, classData, 0, classData.length);
            }
        } else {
            return super.findClass(name);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + File.separator + className.replace('.', File.separatorChar) + ".class";

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, num);
            }
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
