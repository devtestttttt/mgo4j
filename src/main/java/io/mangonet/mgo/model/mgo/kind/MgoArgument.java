package io.mangonet.mgo.model.mgo.kind;

import io.mangonet.mgo.model.mgo.kind.arg.GasCoin;
import io.mangonet.mgo.model.mgo.kind.arg.Input;
import io.mangonet.mgo.model.mgo.kind.arg.NestedResult;
import io.mangonet.mgo.model.mgo.kind.arg.Result;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GasCoin.class, name = "GasCoin"),
        @JsonSubTypes.Type(value = Input.class, name = "Input"),
        @JsonSubTypes.Type(value = Result.class, name = "Result"),
        @JsonSubTypes.Type(value = NestedResult.class, name = "NestedResult")
})
public abstract class MgoArgument {

    protected String type;
}
