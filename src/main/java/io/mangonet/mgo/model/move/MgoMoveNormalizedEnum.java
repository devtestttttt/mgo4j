package io.mangonet.mgo.model.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoMoveNormalizedEnum {

    private MgoMoveAbilitySet abilities;

    private List<MgoMoveStructTypeParameter> typeParameters;

    private List<String> variantDeclarationOrder;

    private Map<String, List<MgoMoveNormalizedField>> variants;
}
