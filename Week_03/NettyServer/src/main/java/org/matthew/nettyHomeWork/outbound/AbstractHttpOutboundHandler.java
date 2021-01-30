package org.matthew.nettyHomeWork.outbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.matthew.nettyHomeWork.filter.HttpRequestFilter;

/**
 * @author Matthew
 * @date 2021-01-30 21:42
 * 定义发送Http 请求
 */
public abstract class AbstractHttpOutboundHandler {

    private static final String WEB_URL = "http://localhost:8888";

    public final void handler(FullHttpRequest fullHttpRequest, ChannelHandlerContext context, HttpRequestFilter filter)
    {
        enFilter(fullHttpRequest, context, filter);
        String responseVa = sendRequest(fullHttpRequest, WEB_URL);
        handleResponse(fullHttpRequest, responseVa, context);
    }

    /**
     * 由各自的子类实现具体发送请求的方式
     * @param fullHttpRequest
     * @param Uri WebURi
     * @return
     */
    public abstract String sendRequest(FullHttpRequest fullHttpRequest, String Uri);


    private void enFilter(FullHttpRequest fullHttpRequest, ChannelHandlerContext context, HttpRequestFilter filter){
        if (filter == null)
            return;
        filter.filter(fullHttpRequest, context);
    }

    private void handleResponse(FullHttpRequest fullHttpRequest, String value, ChannelHandlerContext context)
    {
        FullHttpResponse response = null;
        try {
            if(value == null)
                throw new RuntimeException("Request no response");
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes(CharsetUtil.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-length", response.content().readableBytes());
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        }finally {
            // 写入管道内， send to client
            if (fullHttpRequest != null){
                //如果没有开启keepalive， 直接返回并关闭Channel
                if (!HttpUtil.isKeepAlive(fullHttpRequest)){
                    context.write(response).addListener(ChannelFutureListener.CLOSE);
                }else {
                    if (response != null)
                    {
                        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                    }
                }
            }
        }
    }
}
