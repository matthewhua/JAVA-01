package org.matthew.nettyHomeWork.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author Matthew
 * @date 2021-01-30 21:16
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter{

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("Game", "xzbd");
    }
}
