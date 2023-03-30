package one.panpiper.sample.kafka.springkafka.transformer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import one.panpiper.sample.kafka.springkafka.representation.quote.QuoteRecord;

/**
 * Transforms event records to {@link QuoteRecord}
 */
@Component
public class ConsumerRecordTransformer {

    public QuoteRecord transformConsumerRecord(ConsumerRecord consumerRecord) {
        if (consumerRecord == null) {
            throw new IllegalArgumentException("Invalid consumer record supplied");
        }
        return createQuoteRecord(consumerRecord);
    }

    private QuoteRecord createQuoteRecord(ConsumerRecord consumerRecord) {
        return (QuoteRecord) consumerRecord.value();
    }
}
