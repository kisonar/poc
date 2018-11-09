package com.mossad.kafka.consumer;

import com.mossad.kafka.KafkaPropertiesFactory;
import com.mossad.kafka.KafkaTopicNames;
import com.mossad.kafka.security.CustomProvider;
import java.security.Security;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaConsumerClient {

    public static void main(String[] args) {

        Security.addProvider(new CustomProvider("admin", "1", "Migi custom provider"));

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaPropertiesFactory.getProperties());
        consumer.subscribe(Arrays.asList(KafkaTopicNames.TOPIC_READER));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
