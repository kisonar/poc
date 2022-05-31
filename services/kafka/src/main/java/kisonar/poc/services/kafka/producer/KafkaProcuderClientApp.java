package kisonar.poc.services.kafka.producer;

import kisonar.poc.services.kafka.KafkaProperties;
import kisonar.poc.services.kafka.KafkaTopicNames;
import kisonar.poc.services.kafka.security.CustomProvider;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.security.Security;

public class KafkaProcuderClientApp {

    public static void main(String[] args) {

        Security.addProvider(new CustomProvider("admin", "1", "Migi custom provider"));

        Producer<String, String> producer = new KafkaProducer<>(KafkaProperties.getProducerProperties(KafkaProperties.getCommonProperties()));
        sendMessages(producer);
        sendMessages(producer);
        producer.close();
    }

    private static void sendMessages(Producer<String, String> producer) {
        for (int i = 0; i < 12; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord(KafkaTopicNames.TOPIC_WRITER,
                    String.valueOf(i), String.valueOf(i));
            producer.send(producerRecord);
            System.out.println("Sent message with id: " + i);
        }
    }
}
