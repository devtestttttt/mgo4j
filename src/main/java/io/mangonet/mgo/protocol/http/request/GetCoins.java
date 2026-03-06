package io.mangonet.mgo.protocol.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetCoins {

    private String owner;

    @JsonProperty("coin_type")
    private String coinType;

    private String cursor;

    private Long limit;

}
