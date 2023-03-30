package one.panpiper.sample.kafka.springkafka.transformer;

import org.springframework.stereotype.Component;

import one.panpiper.sample.kafka.springkafka.representation.quote.QuoteRecord;

/**
 * Transforms event records to {@link QuoteRecord}
 */
@Component
public class ProducerRecordTransformer {

    public QuoteRecord transformProducerRecord(Long key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Invalid consumer record supplied");
        }
        return createQuoteRecord(key, value);
    }

    private QuoteRecord createQuoteRecord(Long key, String value) {
        var quoteRecord = new QuoteRecord();
        quoteRecord.setQuoteId(key);
        quoteRecord.setQuote(value);
        return quoteRecord;
    }
}
