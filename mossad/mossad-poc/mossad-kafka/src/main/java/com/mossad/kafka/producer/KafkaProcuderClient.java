package com.mossad.kafka.producer;

import com.mossad.kafka.KafkaPropertiesFactory;
import com.mossad.kafka.KafkaTopicNames;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProcuderClient {

    public static void main(String[] args) {
        Producer<String, String> producer = new KafkaProducer<>(KafkaPropertiesFactory.getProperties());
        for (int i = 0; i < 10012; i++)
            producer.send(new ProducerRecord<>(KafkaTopicNames.TOPIC_WRITER, Integer.toString(i), Integer.toString(i)));
        producer.close();
    }
}
