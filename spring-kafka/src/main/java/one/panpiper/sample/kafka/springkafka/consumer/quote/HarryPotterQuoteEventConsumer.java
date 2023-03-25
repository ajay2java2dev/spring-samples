package one.panpiper.sample.kafka.springkafka.consumer.quote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.panpiper.sample.kafka.springkafka.representation.quote.QuoteRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class HarryPotterQuoteEventConsumer {

    private final ConsumerRecordProcessor consumerRecordProcessor;
    private final KafkaTemplate<Long, QuoteRecord> template;

    @KafkaListener(topics = "ENTERPRISE.PAN.PIPER.HARRY.EVENTS", groupId = "spring-boot-harry-quote-consumer")
    public void consumeEvent(ConsumerRecord<Long, QuoteRecord> record) {

        log.info("processing offset: {}, key: {}, message: {}, headers: {}",
                record.offset(), record.key(), record.value(), record.headers());

        //continue processing here...
        var result = Boolean.FALSE;
        try {
            result = consumerRecordProcessor.processRecord(record);
        } catch (Exception ex) {
            log.error("An exception has occurred: {}", ex.getMessage(), ex);
        }
        validateResponse(record, result);
    }

    private void validateResponse (ConsumerRecord<Long, QuoteRecord> record, boolean processingResult) {
        if (!processingResult) {
            log.error("processed unsuccessfully. key: {}", record.key());
            template.send("ENTERPRISE.PAN.PIPER.HARRY.EVENTS.DLQ", record.key(), record.value());
        } else {
            log.debug("processed successfully. key: {}", record.key());
        }
    }
}
