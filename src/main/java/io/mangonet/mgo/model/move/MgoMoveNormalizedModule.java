package io.mangonet.mgo.model.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoMoveNormalizedModule {

    private long fileFormatVersion;

    private String address;

    private String name;

    private List<MgoMoveModuleId> friends;

    private Map<String, MgoMoveNormalizedStruct> structs;

    private Map<String, MgoMoveNormalizedEnum> enums;

    private Map<String, MgoMoveNormalizedFunction> exposedFunctions;

}
