package com.planning.io.nio.basic;

import java.nio.FloatBuffer;

/**
 * @author planning
 * @date 2020/8/4 7:37
 */
public class UseFloatBuffer {

    public static void main(String[] args) {
        FloatBuffer buffer = FloatBuffer.allocate(5);

        for (int i = 0; i < buffer.capacity(); i++) {
            float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
            buffer.put(f);
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            float f = buffer.get();
            System.out.println(f);
        }
    }
}
