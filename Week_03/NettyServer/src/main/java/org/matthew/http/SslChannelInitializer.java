package org.matthew.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
* @author Matthew
* @date 2021-01-30 8:47
*/
public class SslChannelInitializer extends ChannelInitializer<Channel> {
    private final SslContext context;
    private final boolean startTls;

    public SslChannelInitializer(SslContext context, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        SSLEngine engine = context.newEngine(channel.alloc());
        channel.pipeline().addLast("ssl", // 对于每个SSlHandler 实例都使用Channel 的byteBufAllocator 从SSlContext 获取一个新的SSLEngine
                //讲SSlHandler 作为第一个ChannelHandler 添加到ChannelPipeline
                new SslHandler(engine, startTls));
    }
}
