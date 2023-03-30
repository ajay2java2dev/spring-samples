package one.panpiper.sample.kafka.springkafka.representation.quote;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//sample: https://howtodoinjava.com/kafka/spring-boot-jsonserializer-example/
@Getter
@Setter
@ToString
public class QuoteRecord extends QuoteSchema {

    @JsonProperty("quote_id")
    private Long quoteId;

    @JsonProperty("quote")
    private String quote;

}
