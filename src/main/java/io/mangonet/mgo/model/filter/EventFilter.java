package io.mangonet.mgo.model.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain=true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventFilter {

    @JsonProperty("All")
    private List<EventFilter> all;

    @JsonProperty("Any")
    private List<EventFilter> any;

    @JsonProperty("Sender")
    private String sender;

    @JsonProperty("Transaction")
    private String transactionDigest;

    @JsonProperty("MoveModule")
    private MoveModuleFilter moveModuleFilter;

    @JsonProperty("MoveEventType")
    private String moveEventType;

    @JsonProperty("MoveEventModule")
    private MoveModuleFilter moveEventModuleFilter;

    @JsonProperty("TimeRange")
    private TimeRangeFilter timeRangeFilter;

}
