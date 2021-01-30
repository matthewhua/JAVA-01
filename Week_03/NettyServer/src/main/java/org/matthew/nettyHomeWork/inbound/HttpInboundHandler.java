package org.matthew.nettyHomeWork.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import jdk.internal.dynalink.support.DefaultPrelinkFilter;
import org.matthew.nettyHomeWork.filter.HeaderHttpRequestFilter;
import org.matthew.nettyHomeWork.filter.HttpRequestFilter;
import org.matthew.nettyHomeWork.outbound.AbstractHttpOutboundHandler;
import org.matthew.nettyHomeWork.outbound.httpclient4.HttpOutboundHandler;
import org.matthew.nettyHomeWork.outbound.netty.NettyHttpOutboundHandler;
import org.matthew.nettyHomeWork.outbound.okhttp.OKHttpOutboundHandler;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Matthew
 * @date 2021-01-30 21:24
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    final ConcurrentHashMap<String, AbstractHttpOutboundHandler> cacheMap = new ConcurrentHashMap<>();
    private HttpOutboundHandler handler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();

    public HttpInboundHandler() {

        this.handler = new HttpOutboundHandler();
        cacheMap.put("ok", new OKHttpOutboundHandler());
        cacheMap.put("client", new HttpOutboundHandler());
        NettyHttpOutboundHandler nettyHandler = new NettyHttpOutboundHandler();
        cacheMap.put("netty", nettyHandler);
        cacheMap.put("default", nettyHandler);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            System.out.println("channelRead流量接口请求开始，时间为{}"+  System.currentTimeMillis());
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            System.out.println("接收到的请求url为{}"+ uri);
            if (uri.contains("/test")) {
                return;
            }

            int index = uri.lastIndexOf("/");
            String suffix = uri.substring(index + 1);
            System.out.println("suffix is : " + suffix);
            AbstractHttpOutboundHandler handler = cacheMap.get(suffix);
            if (handler == null)
            {
                handler = cacheMap.get("default");
            }

            handler.handler(fullRequest, ctx, new HeaderHttpRequestFilter());
        }  catch(Exception e) {
        e.printStackTrace();
    } finally {
        ReferenceCountUtil.release(msg);
    }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


}
