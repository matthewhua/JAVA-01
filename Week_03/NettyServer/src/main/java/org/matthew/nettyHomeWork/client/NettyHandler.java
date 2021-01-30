package org.matthew.nettyHomeWork.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author Matthew
 * @date 2021-01-30 23:35
 */
public class NettyHandler extends ChannelInboundHandlerAdapter {
    private final List<String> list;
    private final String uriPath;
    private final FullHttpRequest fullHttpRequest;


    public NettyHandler(List<String> list, String uriPath, FullHttpRequest fullHttpRequest) {
        this.list = list;
        this.uriPath = uriPath;
        this.fullHttpRequest = fullHttpRequest;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        HttpHeaders headers = fullHttpRequest.headers();
        headers.remove("Host");

        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, uriPath);
        request.headers().setAll(headers);
        request.headers().add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
        System.out.println(request.headers().toString());
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            System.out.println("receive response, headers is :" + response.headers().toString());
        }

        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println("receive response content, content is:" + buf.toString(CharsetUtil.UTF_8));
            list.add(buf.toString(CharsetUtil.UTF_8));
            buf.release();
            ctx.close();
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
