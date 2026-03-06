package io.mangonet.mgo.model.object.kind.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.mangonet.mgo.model.object.ObjectRef;
import io.mangonet.mgo.model.object.kind.InputObjectKind;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImmOrOwnedMoveObject implements InputObjectKind {

    @JsonUnwrapped  // Used to unwrap nested structure
    @JsonProperty("ImmOrOwnedMoveObject")
    private ObjectRef immOrOwnedMoveObject;

    @Override
    public String getType() {
        return "ImmOrOwnedMoveObject";
    }
}
