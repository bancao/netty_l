package com.netty.sample.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	 public void channelRead(ChannelHandlerContext ctx, Object msg) {
		String buf = (String) msg; 
		System.out.println(buf);
        ctx.write(buf);
    }
	
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}
