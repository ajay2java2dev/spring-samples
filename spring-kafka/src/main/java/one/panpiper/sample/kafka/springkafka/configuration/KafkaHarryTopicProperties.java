package one.panpiper.sample.kafka.springkafka.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("kafka-harry")
public class KafkaHarryTopicProperties {

    private String topic;
    private String topicRetry;
    private String topicDLQ;
}
