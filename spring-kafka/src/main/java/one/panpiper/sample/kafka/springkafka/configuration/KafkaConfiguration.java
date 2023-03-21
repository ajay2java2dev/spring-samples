package one.panpiper.sample.kafka.springkafka.configuration;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaConfiguration {


    //use this bean to create a KafkaTemplate
//    @Bean
//    public ProducerFactory<String, String> producerFactory () {
//        return new DefaultKafkaProducerFactory<>(
//                Map.of(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
//                        RETRIES_CONFIG, 0,
//                        BUFFER_MEMORY_CONFIG, 33554432,
//                        KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
//                        VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
//        );
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
}
