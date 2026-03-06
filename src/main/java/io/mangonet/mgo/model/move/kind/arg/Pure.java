package io.mangonet.mgo.model.move.kind.arg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.MoveFunctionArgType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pure extends MoveFunctionArgType {

}
