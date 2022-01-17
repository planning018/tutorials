package com.planning.io.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author yxc
 * @date 2021/3/31 14:51
 */
@Slf4j
public class EchoHandler implements Runnable {

    private SocketChannel channel;
    private SelectionKey key;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    private static int RECEIVING = 0, SENDING = 1;
    private int state = RECEIVING;

    public EchoHandler(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        c.configureBlocking(false);
        // int OP_READ = 1 << 0
        key = channel.register(selector, 0);

        // 将 handler 作为选择键的附件
        key.attach(this);

        // 第二步， 注册 Read 就绪事件
        key.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        try{
            if(state == SENDING){
                // 写入通道
                channel.write(byteBuffer);
                // 写完后，准备开始从通道读， byteBuffer 切换成写模式
                byteBuffer.clear();
                // 写完后，注册 read 就绪事件
                key.interestOps(SelectionKey.OP_READ);
                // 写完后，进入接收状态
                state = RECEIVING;
            }else if(state == RECEIVING){
                int length = 0;
                while ((length = channel.read(byteBuffer)) > 0) {
                    log.info(new String(byteBuffer.array(), 0, length));
                }
                //读完后，准备开始写入通道,byteBuffer切换成读模式
                byteBuffer.flip();
                //读完后，注册write就绪事件
                key.interestOps(SelectionKey.OP_WRITE);
                //读完后,进入发送的状态
                state = SENDING;
            }
            // 处理结束了，这里不能关闭 select key，需要重复使用
            // key.cancel();

        }catch(Exception e){
            e.printStackTrace();
            key.cancel();
            try {
                channel.finishConnect();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
