package kisonar.poc.services.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public final class KafkaProperties {

      public static final String KAFKA_HOST = "localhost";
      public static final Integer KAFKA_PORT = 9092;
      public static final String GROUP_ID = "testGroupId";
      public static final String CLIENT_ID = "clientId";

      private KafkaProperties() {
      }

      public static Properties getCommonProperties() {
            Properties props = new Properties();
            props.put("bootstrap.servers", KAFKA_HOST + ":" + KAFKA_PORT);
            return props;
      }

      public static Properties getConsumerProperties(Properties props) {
            props.put("group.id", GROUP_ID);
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            return props;
      }

      public static Properties getProducerProperties(Properties props) {
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
            return props;
      }
}