package io.mangonet.mgo.model.object.kind.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.InputObjectKind;
import io.mangonet.mgo.model.object.kind.input.subtypes.SharedMoveObjectDetail;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SharedMoveObject implements InputObjectKind {

    @JsonProperty("SharedMoveObject")
    private SharedMoveObjectDetail sharedMoveObject;

    @Override
    public String getType() {
        return "SharedMoveObject";
    }

}
