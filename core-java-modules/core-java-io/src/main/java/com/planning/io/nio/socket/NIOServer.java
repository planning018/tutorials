package com.planning.io.nio.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author yxc
 * @since 2020-08-05 11:31
 **/
public class NIOServer {

    public static void main(String[] args) throws Exception {
        // 1. 得到一个 ServerSocketChannel 对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2. 得到一个 Selector 对象
        Selector selector = Selector.open();
        // 3. 绑定一个端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 4. 设置非阻塞方式
        serverSocketChannel.configureBlocking(false);
        // 5. 把 ServerSocketChannel 对象注册给 Selector 对象
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6. 开始干活
        while (true) {
            // 6.1 监控客户端
            if (selector.select(2000) == 0) {
                // nio 非阻塞的优势
                System.out.println("Server: 没有客户端搭理我，我做其他的事情");
                continue;
            }

            // 6.2 得到 SelectKey，判断通道里的事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                // 客户端连接请求事件
                if (key.isAcceptable()) {
                    System.out.println("OP_ACCEPT");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                // 读取客户端数据事件
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    //ByteBuffer buffer = (ByteBuffer) key.attachment();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    System.out.println("客户端发来数据：" + new String(buffer.array()));
                }
                // 6.3 手动从集合中移除当前 key，防止重复处理
                keyIterator.remove();
            }
        }
    }
}