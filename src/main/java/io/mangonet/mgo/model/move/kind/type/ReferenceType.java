package io.mangonet.mgo.model.move.kind.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceType extends MgoMoveNormalizedType {

    @JsonProperty("Reference")
    private MgoMoveNormalizedType reference;
}
