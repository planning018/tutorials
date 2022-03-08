package com.planning.jvm;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * @author yxc
 * @date 2022/3/8 11:20 上午
 */
public class JvmClassloaderPrintPath {

    public static void main(String[] args) {
        // bootstrap classLoader
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器...");
        for (URL url : urLs) {
            System.out.println(" ===> " + url.toExternalForm());
        }

        // ExtClassLoader
        printClassLoader("扩展类加载器", JvmClassloaderPrintPath.class.getClassLoader().getParent());

        // AppClassLoader
        printClassLoader("应用类加载器", JvmClassloaderPrintPath.class.getClassLoader());
    }

    public static void printClassLoader(String name, ClassLoader CL){
        if(CL != null){
            System.out.println(name + " ClassLoader -> " + CL.toString());
            printURLForClassLoader(CL);
        }else{
            System.out.println(name + "ClassLoader -> null");
        }
    }

    public static void printURLForClassLoader(ClassLoader CL){
        Object ucp = inSightField(CL, "ucp");
        Object path = inSightField(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object p : ps){
            System.out.println(" ===> " + p.toString());
        }
    }

    private static Object inSightField(Object obj, String fName) {
        try {
            Field f = null;
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
