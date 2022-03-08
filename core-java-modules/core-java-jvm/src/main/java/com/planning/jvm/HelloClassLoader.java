package com.planning.jvm;

import java.util.Base64;

/**
 * @author yxc
 * @date 2022/3/8 4:27 下午
 */
public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            new HelloClassLoader().findClass("com.planning.jvm.Hello").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloBase64 = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEAFkhlbGxvIENsYXNzIEluaXRpYWxpemUHABkMABoAGwEAFmNvbS9wbGFubmluZy9qdm0vSGVsbG8BABBqYXZhL2xhbmcvT2JqZWN0AQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWACEABQAGAAAAAAACAAEABwAIAAEACQAAAB0AAQABAAAABSq3AAGxAAAAAQAKAAAABgABAAAABwAIAAsACAABAAkAAAAlAAIAAAAAAAmyAAISA7YABLEAAAABAAoAAAAKAAIAAAAKAAgACwABAAwAAAACAA0=";
        byte[] bytes = Base64.getDecoder().decode(helloBase64);
        return defineClass(name, bytes, 0 , bytes.length);
    }
}
