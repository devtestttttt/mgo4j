package io.mangonet.mgo.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.object.kind.Owner;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnedObjectRef {

    private Owner owner;

    private ObjectRef reference;

}
