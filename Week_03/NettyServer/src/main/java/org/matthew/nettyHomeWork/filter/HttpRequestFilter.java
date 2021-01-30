package org.matthew.nettyHomeWork.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author Matthew
 * @date 2021-01-30 20:59
 */
public interface HttpRequestFilter
{
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);

}
