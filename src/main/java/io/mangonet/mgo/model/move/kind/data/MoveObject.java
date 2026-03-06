package io.mangonet.mgo.model.move.kind.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.Data;
import io.mangonet.mgo.model.move.kind.MoveStruct;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveObject implements Data {

    private final String dataType = "moveObject";

    private MoveStruct fields;

    private Boolean hasPublicTransfer;

    private String type;

}
