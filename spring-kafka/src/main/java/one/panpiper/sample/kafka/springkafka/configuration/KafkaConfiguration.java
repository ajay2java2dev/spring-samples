package one.panpiper.sample.kafka.springkafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import one.panpiper.sample.kafka.springkafka.util.ProducerUtil;

@Configuration(proxyBeanMethods = false)
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

    @Bean
    public NewTopic createHarryQuotesTopic() {
        return TopicBuilder.name(ProducerUtil.KAKFA_HARRY_EVENTS_TOPIC)
                .partitions(6)
                .replicas(2)
                .compact()
                .build();
    }

    @Bean
    public NewTopic createHarryQuotesRetryTopic() {
        return TopicBuilder.name(ProducerUtil.KAKFA_HARRY_EVENTS_TOPIC_RETRY)
                .partitions(6)
                .replicas(2)
                .compact()
                .build();
    }

    @Bean
    public NewTopic createHarryQuotesDLQTopic() {
        return TopicBuilder.name(ProducerUtil.KAKFA_HARRY_EVENTS_TOPIC_DLQ)
                .partitions(12)
                .replicas(3)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build();
    }

    @Bean
    public NewTopic createHarryQuotesWordCountStreamTopic() {
        return TopicBuilder.name("streams-wordcount-output")
                .partitions(6)
                .replicas(3)
                .compact()
                .build();
    }
}
