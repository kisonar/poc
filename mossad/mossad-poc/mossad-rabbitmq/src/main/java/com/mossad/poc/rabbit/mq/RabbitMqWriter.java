package com.mossad.poc.rabbit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqWriter {

    public static void main(String[] args) {

        /*
        String userName = "guest";
        String password = "guest";
        String virtualHost = "/";
        String hostName = "10.133.131.152";
        int portNumber = 9001;
        */
        String userName = "vcc_DENA";
        String password = "DEN@2020";
        String virtualHost = "svcc";
        String hostName = "135.3.65.36";
        int portNumber = 5672;
        ConnectionFactory factory = new ConnectionFactory();
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
            System.out.println("Closign channel");
            System.out.println("Closing connection");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                conn.close();
            } catch (IOException e) {

            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }


    }


}
