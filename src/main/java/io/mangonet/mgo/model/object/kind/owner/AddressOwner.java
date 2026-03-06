package io.mangonet.mgo.model.object.kind.owner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.Owner;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressOwner extends Owner {

    @JsonProperty("AddressOwner")
    private String addressOwner;

    public AddressOwner() {
        this.type = "AddressOwner";
    }
}
