package one.panpiper.sample.kafka.springkafka.representation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "ENTERPRISE.PAN.PIPER.HARRY.EVENTS", groupId = "spring-boot-kafka")
    public void consumeEvent(ConsumerRecord<Integer, String> record) {
        log.debug("processing offset: {}, key: {}, from = {}, message: {}, headers: {}",
                record.offset(), record.key(), record.value(), record.value(), record.headers());
        //continue processing here.
    }
}
