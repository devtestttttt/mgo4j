package io.mangonet.mgo.model.move.kind.arg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.move.kind.MoveFunctionArgType;
import io.mangonet.mgo.model.move.kind.arg.enums.ObjectValueKindEnum;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectValueKind extends MoveFunctionArgType {

    @JsonProperty("Object")
    private String object;
}
