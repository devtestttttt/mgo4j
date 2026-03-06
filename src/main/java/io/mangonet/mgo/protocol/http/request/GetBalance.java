package io.mangonet.mgo.protocol.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetBalance {

    private String owner;

    @JsonProperty("coin_type")
    private String coinType;

}
