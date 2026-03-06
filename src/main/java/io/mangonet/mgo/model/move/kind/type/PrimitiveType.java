package io.mangonet.mgo.model.move.kind.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;
import io.mangonet.mgo.model.move.kind.type.enums.PrimitiveEnum;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimitiveType extends MgoMoveNormalizedType {

    public PrimitiveType(String type) {
        if (PrimitiveEnum.find(type) == null) {
            throw new IllegalArgumentException("Invalid primitive type: " + type);
        }
        this.type = type;
    }

}
