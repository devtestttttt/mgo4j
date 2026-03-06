package io.mangonet.mgo.model.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoMoveNormalizedStruct {

    private MgoMoveAbilitySet abilities;

    private List<MgoMoveNormalizedField> fields;

    private List<MgoMoveStructTypeParameter> typeParameters;

}
