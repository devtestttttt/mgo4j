package io.mangonet.mgo.model.object.kind.change;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.ObjectChange;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wrapped implements ObjectChange {

    @JsonProperty("objectId")
    private String objectId;

    @JsonProperty("objectType")
    private String objectType;

    private String sender;

    private final String type = "wrapped";

    private Long version;

}
