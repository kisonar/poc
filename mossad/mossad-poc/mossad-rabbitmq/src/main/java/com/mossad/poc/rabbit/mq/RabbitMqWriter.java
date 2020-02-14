package com.mossad.poc.rabbit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqWriter {

    //static String QUEUE_NAME = "svc-test-2";
    static String QUEUE_NAME = "svcc_dena_dfdr11_filer_stats_q";

    public static void main(String[] args) {
        /*
        String userName = "guest";
        String password = "guest";
        String virtualHost = "virtual-host";
        int portNumber = 9200;
        String hostName = "10.133.131.152";
        */
        //String hostName = "localhost";

        String userName = "svcc_DENA";
        String password = "DEN@2020!";
        String virtualHost = "svcc";
        String hostName = "135.3.65.36";
        int portNumber = 5672;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(hostName);
        factory.setPort(portNumber);
        System.out.println("Starting");
        try (
                Connection conn = factory.newConnection();
                Channel channel = conn.createChannel()
        ) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }


}
