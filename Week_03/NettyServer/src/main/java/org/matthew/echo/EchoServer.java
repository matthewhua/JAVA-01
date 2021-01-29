package org.matthew.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Matthew
 * @date 2021/1/29 11:25
 */
public class EchoServer
{
	private final int port;

	public EchoServer(int port)
	{
		this.port = port;
	}

	public static void main(String[] args) throws Exception
	{
		int port;
		if (args.length != 1)
		{
			System.err.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
			port = 8004;
		}else
		{
			port = Integer.parseInt(args[0]);
		}
		new EchoServer(port).start();
	}



	public void start() throws Exception{
		final EchoServerHandler serverHandler = new EchoServerHandler();
		NioEventLoopGroup group = new NioEventLoopGroup();

		try
		{
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>()
					{
						@Override protected void initChannel(SocketChannel ch) throws Exception
						{
							ch.pipeline().addLast(serverHandler); //可以使用同样的实例
						}
					});
			ChannelFuture sync = b.bind(port).sync();
			sync.channel().closeFuture().sync();
		}
		finally
		{
			group.shutdownGracefully().sync();
		}
	}
}
