package com.planning.io.nio.basic;

import java.nio.ByteBuffer;

/**
 * @author yxc
 * @since 2020-08-04 19:42
 **/
public class CreateArrayBuffer {

    public static void main(String[] args) {
        byte[] array = new byte[1024];

        ByteBuffer buffer = ByteBuffer.wrap(array);

        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');
        buffer.put((byte) 'd');

        buffer.flip();

        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());

    }
}