package com.planning.io.nio.basic;

import java.nio.ByteBuffer;

/**
 * @author yxc
 * @since 2020-08-04 20:13
 **/
public class CreateBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put((byte) 'z');
        buffer.put((byte) 'x');
        buffer.put((byte) 'c');
        buffer.put((byte) 'v');

        buffer.flip();

        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());

    }
}