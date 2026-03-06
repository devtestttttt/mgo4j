package io.mangonet.mgo.model.object.kind.change;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.ObjectChange;
import io.mangonet.mgo.model.object.kind.Owner;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transferred implements ObjectChange {

    private String digest;

    @JsonProperty("objectId")
    private String objectId;

    @JsonProperty("objectType")
    private String objectType;

    private Owner recipient;

    private String sender;

    private final String type = "transferred";

    private Long version;

}
