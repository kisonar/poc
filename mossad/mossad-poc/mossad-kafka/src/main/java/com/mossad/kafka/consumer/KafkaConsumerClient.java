package com.mossad.kafka.consumer;

import com.mossad.kafka.KafkaPropertiesFactory;
import com.mossad.kafka.KafkaTopicNames;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

public class KafkaConsumerClient {

    public static void main (String [] args){

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaPropertiesFactory.getProperties());
        consumer.subscribe(Arrays.asList(KafkaTopicNames.TOPIC_READER));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
}
