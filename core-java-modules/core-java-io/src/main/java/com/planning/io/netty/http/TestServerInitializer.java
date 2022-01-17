package com.planning.io.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author yxc
 * @since 2020-09-03 14:54
 **/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 向管道加入处理器

        // 获取管道
        ChannelPipeline pipeline = channel.pipeline();

        // 加入 netty 提供的 httpServerCodec，其是 netty 提供的处理 http 的编解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        pipeline.addLast("MyTestHttpServerHandler", new TestHttpServerHandler());

        System.out.println("ok....");
    }
}