package one.panpiper.sample.kafka.springkafka.consumer.quote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.panpiper.sample.kafka.springkafka.transformer.ConsumerRecordTransformer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsumerRecordProcessor {

    private final ConsumerRecordTransformer consumerRecordTransformer;

    public boolean processRecord(ConsumerRecord consumerRecord) {
        var quoteRecord = consumerRecordTransformer.transformConsumerRecord(consumerRecord);
        if (quoteRecord != null) {
            //TODO: save to db
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
