package com.mossad.kafka.producer;

import com.mossad.kafka.KafkaPropertiesFactory;
import com.mossad.kafka.KafkaTopicNames;
import com.mossad.kafka.security.CustomProvider;
import java.security.Security;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProcuderClient {

    public static void main(String[] args) {

        Security.addProvider(new CustomProvider("admin", "1", "Migi custom provider"));

        Producer<String, String> producer = new KafkaProducer<>(KafkaPropertiesFactory.getProperties());
        for (int i = 0; i < 10012; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(KafkaTopicNames.TOPIC_WRITER,
                Integer.toString(i), Integer.toString(i));
            producer.send(producerRecord);
            System.out.println("Sent message with id: " + i);
        }
        producer.close();
    }
}
