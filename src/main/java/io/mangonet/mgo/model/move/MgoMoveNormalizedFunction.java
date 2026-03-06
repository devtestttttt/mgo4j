package io.mangonet.mgo.model.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoMoveNormalizedFunction {

    private Boolean isEntry;

    private List<MgoMoveNormalizedType> parameters;

    private List<MgoMoveNormalizedType> returnTypes;

    private List<MgoMoveAbilitySet> typeParameters;

    private String visibility;
}
