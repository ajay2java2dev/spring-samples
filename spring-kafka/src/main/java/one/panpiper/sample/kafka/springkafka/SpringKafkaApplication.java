package one.panpiper.sample.kafka.springkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import one.panpiper.sample.kafka.springkafka.configuration.KafkaHarryTopicProperties;

@SpringBootApplication
@EnableKafka
@EnableKafkaStreams
@EnableConfigurationProperties(KafkaHarryTopicProperties.class)
public class SpringKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaApplication.class, args);
    }

}
