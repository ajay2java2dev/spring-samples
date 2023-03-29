package one.panpiper.sample.kafka.springkafka.consumer.quote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.panpiper.sample.kafka.springkafka.configuration.KafkaHarryTopicProperties;
import one.panpiper.sample.kafka.springkafka.representation.quote.QuoteRecord;
import one.panpiper.sample.kafka.springkafka.transformer.ConsumerRecordTransformer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsumerRecordStreamsProcessor {

    @Value("${kafka-harry.topic}")
    private String sourceTopic;
    private final ConsumerRecordTransformer consumerRecordTransformer;

    //StreamsBuilder automatically injected.
    @Autowired
    public void process(StreamsBuilder streamsBuilder) {

        //Serializer / Deserializer (serde) for String, long, Custom Object types
        //TODO: https://medium.com/@agvillamizar/implementing-custom-serdes-for-java-objects-using-json-serializer-and-deserializer-in-kafka-streams-d794b66e7c03
        final Serde<Long> longSerde = Serdes.Long();
        final Serde<String> stringSerde = Serdes.String();
        final Serde<QuoteRecord> quoteRecordJsonSerde = new JsonSerde<QuoteRecord>(QuoteRecord.class);


        //Construct a 'KStream' from the input topic "streams-plaintext-input", where message values
        //represent lines of text (for the sake of this example, we ignore whatever may be stored in the message keys
        KStream<Long, QuoteRecord> textLines = streamsBuilder.stream(sourceTopic, Consumed.with(longSerde, quoteRecordJsonSerde));


        KTable<String, Long> wordCounts = textLines
                // Split each text line, by whitespace, into words.
                // The textLines have both key and values. we can ignore the key and just concentrate on the value as shown below
                .flatMapValues(value -> Arrays.asList(value.getQuote().toLowerCase().split("\\W+")))
                // We use 'groupBy' to ensure the words are available as message keys
                .groupBy((key, value) -> value, Grouped.with(stringSerde, stringSerde))
                // Count the occurences of each word
                .count();


        //Convert the 'KTable<String, Long>' into a 'KStream<String, Long>' and write to the output topic.
        wordCounts.toStream().to("streams-wordcount-output", Produced.with(stringSerde, longSerde));
    }
}
