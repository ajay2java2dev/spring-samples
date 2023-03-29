package one.panpiper.sample.kafka.springkafka.configuration;

import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;

import java.util.Map;

//TODO: Refer https://www.baeldung.com/spring-boot-kafka-streams for more details
//Disabled since its not used. Instead topology defined in ConsumerRecordStreamsProcessor
//@Configuration
public class KafkaStreamsConfiguration {

    @Value(value = "${spring.kafka.properties.bootstrap.servers}")
    private String bootstrapAddress;

//    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//    public KafkaStreamsConfiguration kafkaStreamsConfiguration() {
//        return new KafkaStreamsConfiguration(Map.of(
//                StreamsConfig.APPLICATION_ID_CONFIG, "default",
//                StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
//                StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass().getName(),
//                StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName(),
//                StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName()
//        ));
//    }
}
