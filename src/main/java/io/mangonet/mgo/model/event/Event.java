package io.mangonet.mgo.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private EventId id;

    private String packageId;

    private Object parsedJson;

    private String sender;

    private BigInteger timestampMs;

    private String transactionModule;

    private String type;

    private String bcs;

    private String bcsEncoding;

}
