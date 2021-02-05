package org.matthew.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Matthew
 * @date 2021/2/1 16:31
 */
public class ChannelClient
{

	//当一个应用程序（如一个代理服务器）必须要和组织现有的系统（如 Web 服务或者数据库）集成
	//时，就可能发生这种情况。在这种情况下，将需要从已经被接受的子 Channel 中引导一个客户
	//端 Channel。

	public static void main(String[] args)
	{
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
				.channel(NioServerSocketChannel.class)
				.childHandler(new SimpleChannelInboundHandler<ByteBuf>()
				{
					@Override protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception
					{
						System.out.println("Received data");
						msg.clear();
					}
				});
		ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
		future.addListener(new ChannelFutureListener()
		{
			@Override public void operationComplete(ChannelFuture future) throws Exception
			{
				if (future.isSuccess())
			}
		})
	}


}
