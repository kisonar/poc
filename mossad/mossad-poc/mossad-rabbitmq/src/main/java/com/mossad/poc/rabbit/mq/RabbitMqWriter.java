package com.mossad.poc.rabbit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqWriter {

    public static void main(String[] args) {

        String userName = "guest";
        String password = "guest";
        String virtualHost = "/";
        String hostName = "10.133.131.152";
        int portNumber = 9001;

        ConnectionFactory factory = new ConnectionFactory();
// "guest"/"guest" by default, limited to localhost connections
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(hostName);
        factory.setPort(portNumber);

        Channel channel = null;
        Connection conn = null;
        System.out.println("Starting");
        try {
            conn = factory.newConnection();
            System.out.println("connection");
            channel = conn.createChannel();
            System.out.println("channel");
            channel.close();
            System.out.println("Closign channel");
            conn.close();
            System.out.println("Closing connection");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {

        }


    }


}
