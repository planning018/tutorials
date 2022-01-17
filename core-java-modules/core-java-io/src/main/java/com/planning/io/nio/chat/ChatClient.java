package com.planning.io.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author yxc
 * @since 2020-08-08 17:40
 **/
public class ChatClient {

    /**
     * 服务器地址
     */
    private static final String HOST = "127.0.0.1";
    /**
     * 服务器端口
     */
    private static final int PORT = 9999;
    /**
     * 网络通道
     */
    private SocketChannel socketChannel;
    /**
     * 聊天用户名
     */
    private String userName;

    public ChatClient() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress(HOST, PORT);

        // 连接服务器
        if(!socketChannel.connect(address)){
            while(!socketChannel.finishConnect()){
                System.out.println("Client:连接服务器端的同时，我还可以干别的一些事情");
            }
        }

        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println("---------------Client(" + userName + ") is ready---------------");
    }

    /**
     * 向服务器端发送数据
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg) throws IOException {
        if("bye".equalsIgnoreCase(msg)){
            socketChannel.close();
            return;
        }

        msg = userName + " say: " + msg;
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(buffer);
    }

    /**
     * 从服务器端接收数据
     * @throws IOException
     */
    public void receiveMsg() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int size = socketChannel.read(buffer);
        if(size > 0){
            String msg = new String(buffer.array());
            System.out.println(msg.trim());
        }
    }

}