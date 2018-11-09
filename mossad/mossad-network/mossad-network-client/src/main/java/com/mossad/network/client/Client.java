/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mossad.network.client;

import com.mossad.network.library.coding.TimeStampDecoder;
import com.mossad.network.library.coding.TimeStampEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author mmigdal
 */
public class Client {

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);

        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeStampEncoder(), new TimeStampDecoder(), new ClientHandler());
            }
        });

        String serverIp = "127.0.0.1";
        b.connect(serverIp, 19000);
        System.out.print("Client has been run ...");
    }

}
