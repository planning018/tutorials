package com.planning.io.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * @author yxc
 * @since 2020-08-08 16:30
 **/
public class ChatServer {

    /**
     * 监听通道
     */
    private ServerSocketChannel listenerChannel;
    /**
     * 选择器对象
     */
    private Selector selector;
    /**
     * 服务器端口
     */
    private static final int PORT = 9999;

    public ChatServer() {
        try {
            listenerChannel = ServerSocketChannel.open();
            selector = Selector.open();
            listenerChannel.bind(new InetSocketAddress(PORT));
            listenerChannel.configureBlocking(false);
            // 将 选择器 绑定到 监听通道 并监听 accept 事件
            listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
            printInfo("Chat server is ready....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            // 不停监控
            while (true) {
                if (selector.select(2000) == 0) {
                    System.out.println("Server: 没有客户端找我，我就干别的事情");
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    // 连接请求事件
                    if (key.isAcceptable()) {
                        SocketChannel sc = listenerChannel.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        System.out.println(sc.getRemoteAddress().toString().substring(1) + "上线了....");
                    }
                    // 读取数据事件
                    if (key.isReadable()) {
                        readMsg(key);
                    }
                    // 一定要把当前 key 删掉，防止重复处理
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取客户端发来的消息，并广播出去
     * @param key
     * @throws IOException
     */
    private void readMsg(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = channel.read(buffer);
        if (count > 0) {
            String msg = new String(buffer.array());
            printInfo(msg);
            // 发广播
            brandCast(channel, msg);
        }
    }

    /**
     * 给所有的客户端发广播
     * @param channel
     * @param msg
     * @throws IOException
     */
    private void brandCast(SocketChannel channel, String msg) throws IOException {
        System.out.println("服务器发送了广播...");
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != channel) {
                SocketChannel destChannel = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                destChannel.write(buffer);
            }
        }
    }

    private void printInfo(String str) { //往控制台打印消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("[" + sdf.format(new Date()) + "] -> " + str);
    }

    public static void main(String[] args) throws Exception {
        new ChatServer().start();
    }
}