package org.matthew.nettyHomeWork;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.matthew.nettyHomeWork.inbound.HttpInboundHandler;

/**
 * @author Matthew
 * @date 2021-01-30 20:34
 */
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {

    /**
     *
     * 当Channel初始创建完毕后就会触发该方法的执行， 用于初始化Channel
     * @param ch
     * @throws Exception
     * @see  org.matthew.http.HttpPipelineInitializer
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //从channel中获取pipeline
        ChannelPipeline pipeline = ch.pipeline();
        //将HttpServerCodec处理器放入到pipeline的最后
        //HttpServerCodec是解码器和编码器， 将客户端发送过来的server端的二进制数据变为ByteBuffer格式的数据， 并且
        //再将处理后的ByteBuffer格式的数据再转换成二进制的数据， 包含了HttpRequestDecoder和HttpResponseEncoder
        // 单独处理可看 see的内容
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        // 将自定义的处理器放到Pipeline的最后
        pipeline.addLast(new HttpInboundHandler());

    }
}
