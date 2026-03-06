package io.mangonet.mgo.model.move.kind.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VectorType extends MgoMoveNormalizedType {

    @JsonProperty("Vector")
    private MgoMoveNormalizedType vector;

}
