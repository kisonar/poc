package kisonar.poc.services.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqWriter {

    private final static String QUEUE_NAME = "svcc_dena_dfdr11_filer_stats_q";

    public static void main(String[] args) {
        String userName = "";
        String password = "";
        String virtualHost = "";
        String hostName = "";
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