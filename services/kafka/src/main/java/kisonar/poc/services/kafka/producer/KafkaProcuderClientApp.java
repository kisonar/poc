package kisonar.poc.services.kafka.producer;

import kisonar.poc.services.kafka.KafkaProperties;
import kisonar.poc.services.kafka.KafkaTopicNames;
import kisonar.poc.services.kafka.security.CustomProvider;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.security.Security;

public class KafkaProcuderClientApp {

       static void main(String[] args) {
             Security.addProvider(new CustomProvider("admin", "1", "Migi custom provider"));
             var producer = new KafkaProducer<String, String>(KafkaProperties.getProducerProperties(KafkaProperties.getCommonProperties()));
             sendMessages(producer);
             sendMessages(producer);
             producer.close();
       }

       private static void sendMessages(Producer<String, String> producer) {
             for (int i = 0; i < 12; i++) {
                   var producerRecord = new ProducerRecord<>(KafkaTopicNames.TOPIC_WRITER,
                           String.valueOf(i), String.valueOf(i));
                   producer.send(producerRecord);
                   System.out.printf("Sent message with id: %d%n", i);
             }
       }
}
