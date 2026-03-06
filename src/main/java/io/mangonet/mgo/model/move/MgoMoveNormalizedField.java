package io.mangonet.mgo.model.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoMoveNormalizedField {

    private String name;

    private MgoMoveNormalizedType type;

}
