package io.mangonet.mgo.model.move.kind.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MutableReferenceType extends MgoMoveNormalizedType {

    @JsonProperty("MutableReference")
    private MgoMoveNormalizedType mutableReference;
}
