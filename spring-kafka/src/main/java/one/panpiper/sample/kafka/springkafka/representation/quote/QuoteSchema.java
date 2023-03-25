package one.panpiper.sample.kafka.springkafka.representation.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuoteSchema {

    @JsonProperty("$schema")
    private final String schema = "http://json-schema.org/draft-07/schema#";

    @JsonProperty("$id")
    private final String id = "http://example.com/myURI.schema.json";
}
