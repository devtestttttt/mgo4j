package io.mangonet.mgo.model.object.kind.owner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.object.kind.Owner;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Immutable extends Owner {

    public Immutable() {
        this.type = "Immutable";
    }
}
