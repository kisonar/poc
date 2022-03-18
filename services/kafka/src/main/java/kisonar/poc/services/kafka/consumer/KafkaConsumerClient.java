package kisonar.poc.services.kafka.consumer;

import kisonar.poc.services.kafka.KafkaPropertiesFactory;
import kisonar.poc.services.kafka.KafkaTopicNames;
import kisonar.poc.services.kafka.security.CustomProvider;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.security.Security;
import java.time.Duration;
import java.util.Arrays;

public class KafkaConsumerClient {

    public static void main(String[] args) {
        Security.addProvider(new CustomProvider("admin", "1", "Migi custom provider"));
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaPropertiesFactory.getProperties());
        consumer.subscribe(Arrays.asList(KafkaTopicNames.TOPIC_READER));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1L));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
