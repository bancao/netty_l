package com.netty.sample.heart;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartBeatClient {

	public static void main(String[] args) {
		
		EventLoopGroup elg = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(elg)
			 .channel(NioSocketChannel.class)
			 .option(ChannelOption.TCP_NODELAY, true)
			 .handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));
					p.addLast(new ClientConnectWatchDog());
					p.addLast(new StringDecoder());
					p.addLast(new StringEncoder());
					p.addLast(new HeartBeatClientHandler());
				}
			});
			
			ChannelFuture f = b.connect("127.0.0.1", 3349).sync();
		} catch (InterruptedException e) {
		} finally {
		}
	}
}
