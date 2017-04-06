package com.netty.sample.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class EchoClient {

	public static void main(String[] args) {
		EventLoopGroup client = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(client)
			 .channel(NioSocketChannel.class)
			 .option(ChannelOption.TCP_NODELAY, true)
			 .handler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new StringDecoder()); 
					p.addLast(new StringEncoder());
					p.addLast(new EchoClientHandler());
				
				}
			});
			
			ChannelFuture f = b.connect("10.168.1.202", 4463).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		} finally {
			client.shutdownGracefully();
		}
	}
}
