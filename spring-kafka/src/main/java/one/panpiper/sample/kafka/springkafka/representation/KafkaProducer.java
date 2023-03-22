package one.panpiper.sample.kafka.springkafka.representation;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationStartupAware;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<Integer, String> template;

    Faker faker;

    @EventListener(ApplicationStartedEvent.class)
    public void generate() {
        faker = faker.instance();

        final Flux<Long> interval = Flux.interval(Duration.ofMillis(1000));

        final Flux<String> quotes = Flux.fromStream(Stream.generate(() -> faker.harryPotter().quote()));

        Flux.zip(interval, quotes)
                .map((Function<Tuple2<Long, String>, Object>) objects -> {
                    log.debug("Event object : {}, value: {}, sent on: {}",objects.getT1(), objects.getT2(), Instant.now());
                    return template.send("ENTERPRISE.PAN.PIPER.HARRY.EVENTS", faker.random().nextInt(42), objects.getT2());
                })
                .blockLast();
    }
}
