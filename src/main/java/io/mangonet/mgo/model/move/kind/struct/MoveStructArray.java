package io.mangonet.mgo.model.move.kind.struct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.MoveStruct;
import io.mangonet.mgo.model.move.kind.MoveValue;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveStructArray extends MoveStruct {

    private List<MoveValue> values;
}
