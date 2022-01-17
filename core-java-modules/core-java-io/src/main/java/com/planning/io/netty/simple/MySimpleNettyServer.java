package com.planning.io.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yxc
 * @since 2020-08-13 19:46
 **/
public class MySimpleNettyServer {

    public static void main(String[] args) throws Exception {
        // 创建 bossGroup 和 workGroup
        /*
         * 说明：
         *  1. 创建两个线程组 bossGroup 和 workGroup
         *  2. bossGroup 只是处理连接请求，业务处理 交给 workGroup
         *  3. 两个 group 都是无限循环
         *  4. 下面分别设置 bossGroup 和 workGroup 的子线程（nioEventLoop）的个数
         *      workGroup 默认是 CPU 核数 * 2
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try{
            // 创建服务器端的启动对象，并配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 使用链式编程来设置
            bootstrap.group(bossGroup, workGroup)
                    // 使用 NioServerSocketChannel 来作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列的连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置线程保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // .handler(null)   该 hander 对应 bossGroup
                    // 创建一个通道初始化对象（匿名对象）
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("客户 socketChannel hashcode = " + socketChannel.hashCode());
                            /*
                             * 可以使用一个集合管理 SocketChannel，再推送消息时，可以将业务加入到各个 channel 对应的 NioEventloop 的 taskQueue 或者 scheduleTaskQueue
                             *
                             *  给 pipeline 设置处理器
                             */
                            socketChannel.pipeline().addLast(new MySimpleNettyServerHandler());
                        }
                    });
            System.out.println("服务器 ready....");

            // 绑定一个端口并且同步，可得到一个 ChannelFuture 对象
            ChannelFuture channelFuture = bootstrap.bind(6889).sync();
            // 给 channelFuture 注册监听器，监听我们关心的事件
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        System.out.println("监听端口成功");
                    }else {
                        System.out.println("监听端口失败");
                    }
                }
            });

            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}