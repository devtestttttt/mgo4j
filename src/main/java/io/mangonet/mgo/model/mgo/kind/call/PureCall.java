package io.mangonet.mgo.model.mgo.kind.call;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.mgo.kind.MgoCallArg;
import io.mangonet.mgo.model.mgo.kind.call.enums.TypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PureCall extends MgoCallArg {

    private Object value; // MgoJsonValue -> Object

    private String valueType;

    public PureCall() {
        this.type = TypeEnum.PURE.getType();
    }
}
