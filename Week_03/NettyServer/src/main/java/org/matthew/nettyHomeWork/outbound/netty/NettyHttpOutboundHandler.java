package org.matthew.nettyHomeWork.outbound.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;

import io.netty.handler.codec.http.HttpClientCodec;
import org.matthew.nettyHomeWork.client.NettyHandler;
import org.matthew.nettyHomeWork.outbound.AbstractHttpOutboundHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew
 * @date 2021-01-30 23:09
 */
public class NettyHttpOutboundHandler extends AbstractHttpOutboundHandler {


    @Override
    public String sendRequest(FullHttpRequest fullHttpRequest, String Uri) {
        System.out.println("NettyHttp 运行啦.....");

        NioEventLoopGroup work = new NioEventLoopGroup();

        List<String> list = new ArrayList<>();
        try {
            URI uri = new URI(Uri);
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new HttpClientCodec());
                            pipeline.addLast(new NettyHandler(list, uri.getPath(), fullHttpRequest));
                        }
                    });

            ChannelFuture future = bootstrap.connect(uri.getHost(), uri.getPort()).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            return "";
        } finally {
            work.shutdownGracefully();
        }
        return list.get(0);
    }

}
