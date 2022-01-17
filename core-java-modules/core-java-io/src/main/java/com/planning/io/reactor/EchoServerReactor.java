package com.planning.io.reactor;

import com.planning.io.utils.Constant;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Reactor 反应器
 *
 * @author yxc
 * @date 2021/3/31 14:16
 */
@Slf4j
public class EchoServerReactor implements Runnable{

    private Selector selector;
    private ServerSocketChannel serverSocket;

    public EchoServerReactor() throws IOException {
        // Reactor 初始化
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(Constant.HOST, Constant.PORT);
        serverSocket.socket().bind(address);
        log.info("服务端开始监听：{}", address);
        serverSocket.configureBlocking(false);

        // 分布处理，第一步，接收 accept 事件
        SelectionKey key = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        // attach callback object, AcceptorHandler
        key.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while(iterator.hasNext()){
                    // Reactor 负责 dispatch 收到的事件
                    SelectionKey key = iterator.next();
                    dispatch(key);
                }
                selectedKeys.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) {
        Runnable handler = (Runnable) key.attachment();
        // 调用之前 attach 绑定到选择键的 handler 处理器对象
        if(Objects.nonNull(handler)){
            handler.run();
        }
    }

    /**
     * Handler: 新连接处理器
     */
    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                log.info("接收到一个请求");
                if(Objects.nonNull(channel)){
                    new EchoHandler(selector, channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }
}
