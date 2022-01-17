package com.planning.io.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author yxc
 * @since 2020-08-05 11:16
 **/
public class NIOClient {

    public static void main(String[] args) throws Exception {
        // 1. 得到一个网络通道
        SocketChannel channel = SocketChannel.open();
        // 2. 设置非阻塞方式
        channel.configureBlocking(false);
        // 3. 提供服务器端的 IP 地址和端口
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        // 4. 连接服务器
        if (!channel.connect(address)) {
            // nio 非阻塞的优势
            while (!channel.finishConnect()) {
                System.out.println("Client: 连接服务器的同时，还可以做一些额外的事情");
            }
        }
        //5. 得到一个缓冲区并存入数据
/*        String msg="hello,Server";
        ByteBuffer writeBuf = ByteBuffer.wrap(msg.getBytes());
        //6. 发送数据
        channel.write(writeBuf);
        // 防止立即结束
        TimeUnit.SECONDS.sleep(3);*/

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();
            if ("bye".equals(message)) {
                channel.close();
                // 发现了一个新的问题，此处 close 后，server 仍可以接收到 OP_READ 触发事件
                return;
            }

            ByteBuffer wrap = ByteBuffer.wrap(message.getBytes());
            try {
                channel.write(wrap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 6. 关闭相关流和通道

        /*
        // 尝试使用 scanner 输入信息，可是服务端收到的数据不对
        // 2021.03.31 找出原因，不是 client 的问题，是 server 用的 SelectionKey 的 attachment 转换的 ByteBuffer
        // 所以数据总是累加
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String msg = scanner.nextLine();
            ByteBuffer writeBuf = ByteBuffer.wrap(msg.getBytes());
            channel.write(writeBuf);
            writeBuf.clear();
        }*/
    }
}