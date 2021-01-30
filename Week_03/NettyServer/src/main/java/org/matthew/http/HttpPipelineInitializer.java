package org.matthew.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author Matthew
 * @date 2021-01-30 8:52
 * 构建Netty 的 HTTP 程序
 */
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {
    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        if (client){ //是否是客户端
            pipeline.addLast("decoder", new HttpResponseDecoder());// 出理来自服务器的响应
            pipeline.addLast("encoder", new HttpRequestEncoder());  // 想服务器发送请求
        }else { //服务器
            pipeline.addLast("decoder", new HttpRequestDecoder()); //接受来自客户端的请求
            pipeline.addLast("encoder", new HttpResponseEncoder());
        }
    }
}
