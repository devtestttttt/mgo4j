package io.mangonet.mgo.model.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoMoveStructTypeParameter {

    private MgoMoveAbilitySet constraints;

    private Boolean isPhantom;
}
