package io.mangonet.mgo.model.move.kind.struct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.MoveStruct;
import io.mangonet.mgo.model.move.kind.MoveValue;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveStructMap extends MoveStruct {

    private Map<String, MoveValue> values;
}
