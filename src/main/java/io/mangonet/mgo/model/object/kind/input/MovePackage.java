package io.mangonet.mgo.model.object.kind.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.InputObjectKind;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovePackage implements InputObjectKind {

    @JsonProperty("MovePackage")
    private String movePackage;

    @Override
    public String getType() {
        return "MovePackage";
    }
}
