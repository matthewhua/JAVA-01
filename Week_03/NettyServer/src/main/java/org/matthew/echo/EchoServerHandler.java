package org.matthew.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @author Matthew
 * @date 2021/1/29 11:09
 */
@ChannelHandler.Sharable  // 可以被多个Channel 安全的共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter
{

	@Override public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Server received: " + in.toString(StandardCharsets.UTF_8)); // (记录到控制台)
		ctx.write(in); //写进管道里 不冲刷出站消息
	}

	@Override public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
	{
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) //将未决消息冲刷到远程节点，
				.addListener(ChannelFutureListener.CLOSE);
	}

	@Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		cause.printStackTrace();
		ctx.close();
	}
}
