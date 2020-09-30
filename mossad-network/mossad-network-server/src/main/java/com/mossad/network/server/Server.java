package com.mossad.network.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mossad.network.library.coding.TimeStampDecoder;
import com.mossad.network.library.coding.TimeStampEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import java.io.IOException;

/**
 * @author mmigdal
 */
public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);

        // ===========================================================
        // 1. define a separate thread pool to execute handlers with
        //    slow business logic. e.g database operation
        // ===========================================================
        final EventExecutorGroup group = new DefaultEventExecutorGroup(3); //thread pool of 1500

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("idleStateHandler", new IdleStateHandler(0, 0, 5)); // add with name
                pipeline.addLast(new TimeStampEncoder()); // add without name, name auto generated
                pipeline.addLast(new TimeStampDecoder()); // add without name, name auto generated

                //===========================================================
                // 2. run handler with slow business logic
                //    in separate thread from I/O thread
                //===========================================================
                pipeline.addLast(group, "serverHandler", new ServerHandler());
            }
        });

        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.bind(19000).sync();
        System.out.println("Server has been run");
    }


}
