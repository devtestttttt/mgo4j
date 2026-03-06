package io.mangonet.mgo.model.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoMoveNormalizedModuleByPackage {

    private final Map<String, MgoMoveNormalizedModule> modules;

}
