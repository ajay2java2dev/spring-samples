package one.panpiper.sample.kafka.springkafka.producer.quote;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.panpiper.sample.kafka.springkafka.representation.quote.QuoteRecord;
import one.panpiper.sample.kafka.springkafka.transformer.ProducerRecordTransformer;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@Component
@RequiredArgsConstructor
@Slf4j
public class HarryPotterQuoteEventProducer {

    private final KafkaTemplate<Long, QuoteRecord> template;
    private final ProducerRecordTransformer producerRecordTransformer;

    Faker faker;

    @EventListener(ApplicationStartedEvent.class)
    public void generate() {
        faker = Faker.instance();

        //every 10s produce an event
        final Flux<Long> interval = Flux.interval(Duration.ofMillis(10000));

        final Flux<String> quotes = Flux.fromStream(Stream.generate(() -> faker.harryPotter().quote()));

        Flux.zip(interval, quotes)
                .map((Function<Tuple2<Long, String>, Object>) objects -> {
                    log.debug("Event object : {}, value: {}, sent on: {}", objects.getT1(), objects.getT2(),
                            Instant.now());

                    var randomKey = faker.random().nextLong(42);
                    var producerRecord = producerRecordTransformer.transformProducerRecord(randomKey, objects.getT2());

                    return template.send("ENTERPRISE.PAN.PIPER.HARRY.EVENTS", randomKey, producerRecord);
                })
                .blockLast();
    }
}
