package one.panpiper.sample.kafka.springkafka.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("kafka-harry")
public class KafkaHarryTopicProperties {

    private String topic;
    private String topicRetry;
    private String topicDLQ;
}
