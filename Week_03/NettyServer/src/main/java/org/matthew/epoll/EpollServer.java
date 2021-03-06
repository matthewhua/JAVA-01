package org.matthew.epoll;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.matthew.echo.EchoServerHandler;

import java.net.InetSocketAddress;

/**
 * @author Matthew
 * @date 2021/1/29 11:25
 */
public class EpollServer
{
	private final int port;

	public EpollServer(int port)
	{
		this.port = port;
	}

	public static void main(String[] args) throws Exception
	{
		int port;
		if (args.length != 1)
		{
			System.err.println("Usage: " + EpollServer.class.getSimpleName() + "<port>");
			port = 8004;
		}else
		{
			port = Integer.parseInt(args[0]);
		}
		new EpollServer(port).start();
	}




	public void start() throws Exception{
		final EchoServerHandler serverHandler = new EchoServerHandler();
		EpollEventLoopGroup group = new EpollEventLoopGroup();  //epoll-server 的loop组
		DefaultEventLoopGroup local = new DefaultEventLoopGroup(); // 本地性能基准 测试
		try
		{
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
					.channel(EpollServerSocketChannel.class) //epoll-server 的channel
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
