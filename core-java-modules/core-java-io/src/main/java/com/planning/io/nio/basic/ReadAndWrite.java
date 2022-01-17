package com.planning.io.nio.basic;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author planning
 * @date 2020/8/5 7:44
 */
public class ReadAndWrite {

    /**
     * 往本地文件中写数据
     * @throws Exception exception
     */
    @Test
    public void testWrite() throws Exception {
        // 1. 创建输出流
        FileOutputStream fos = new FileOutputStream("readAndWrite.txt");
        // 2. 从流中得到一个通道
        FileChannel fc = fos.getChannel();
        // 3. 提供一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 4. 往缓冲区中写入数据
        String str = "hello.nio";
        buffer.put(str.getBytes());
        // 5. 翻转缓冲区
        buffer.flip();
        // 6. 把缓冲区写到通道中
        fc.write(buffer);
        // 7. 关闭流
        fos.close();
    }

    /**
     * 从本地文件中读取数据
     * @throws Exception exception
     */
    @Test
    public void testRead() throws Exception{
        File file = new File("readAndWrite.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        // 从通道里读取数据并存到缓冲区中
        fc.read(buffer);
        System.out.println(new String(buffer.array()));
        fis.close();
    }

    /**
     * 使用 nio 实现文件复制
     * @throws Exception
     */
    @Test
    public void testCopyFile() throws Exception{
        FileInputStream fis = new FileInputStream("readAndWrite.txt");
        FileOutputStream fos = new FileOutputStream("CopyFile.txt");

        FileChannel sourceFc = fis.getChannel();
        FileChannel destFc = fos.getChannel();

        // 复制
        destFc.transferFrom(sourceFc, 0, sourceFc.size());

        fis.close();
        fos.close();
    }

    /**
     * nio 复制文件，不直接使用 api
     * @throws Exception exception
     */
    @Test
    public void testCopyFileOrigin() throws Exception {
        FileInputStream fis = new FileInputStream("readAndWrite.txt");
        FileOutputStream fos = new FileOutputStream("CopyFileOrigin.txt");

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while(true){
            buffer.clear();
            // 此处对于通道 Channel 来说是读数据，但是对于 Buffer 而言，是写数据
            int read = fisChannel.read(buffer);
            if(read == -1){
                break;
            }
            buffer.flip();
            fosChannel.write(buffer);
        }

        fis.close();
        fos.close();
    }
}
