package org.matthew.echo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author Matthew
 * @date 2021/1/29 11:56
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuffer>
{

	// 当被通知Channel 活跃的时候  发送的第一条消息
	@Override public void channelActive(ChannelHandlerContext ctx) throws Exception
	{
		ctx.writeAndFlush(Unpooled.copiedBuffer("Matthew!", StandardCharsets.UTF_8));
	}

	@Override protected void channelRead0(ChannelHandlerContext ctx, ByteBuffer msg) throws Exception
	{
		System.out.println("Client received :" + msg.toString());
	}

	@Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		cause.printStackTrace();
		ctx.close();
	}
}
