package com.mossad.network.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import kisonar.poc.network.netty.library.LoopBackTimeStamp;

/**
 * @author mmigdal
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        LoopBackTimeStamp ts = (LoopBackTimeStamp) msg;
        System.out.println("Cient received message..");
        ctx.writeAndFlush(ts); //recieved message sent back directly
        System.out.println("CLient write and flush");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}