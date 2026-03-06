package io.mangonet.mgo.model.object.kind.owner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.Owner;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectOwner extends Owner {

    @JsonProperty("ObjectOwner")
    private String objectId;

    public ObjectOwner() {
        this.type = "ObjectOwner";
    }
}
