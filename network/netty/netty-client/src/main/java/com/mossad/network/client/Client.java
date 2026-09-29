package com.mossad.network.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import kisonar.poc.network.netty.library.TimeStampDecoder;
import kisonar.poc.network.netty.library.TimeStampEncoder;

public class Client {

       static void main(String[] args) {
             var workerGroup = new NioEventLoopGroup();
             var b = new Bootstrap();
             b.group(workerGroup);
             b.channel(NioSocketChannel.class);
             b.handler(new ChannelInitializer<SocketChannel>() {
                   @Override
                   public void initChannel(SocketChannel ch) {
                         ch.pipeline().addLast(new TimeStampEncoder(), new TimeStampDecoder(), new ClientHandler());
                   }
             });

             var serverIp = "127.0.0.1";
             b.connect(serverIp, 19000);
             System.out.print("Client has been run ...");
       }
}