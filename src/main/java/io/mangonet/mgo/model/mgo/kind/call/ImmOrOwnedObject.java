package io.mangonet.mgo.model.mgo.kind.call;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.mgo.kind.MgoCallArg;
import io.mangonet.mgo.model.mgo.kind.call.enums.ObjectTypeEnum;
import io.mangonet.mgo.model.mgo.kind.call.enums.TypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImmOrOwnedObject extends MgoCallArg {

    @JsonProperty("objectType")
    private String objectType = ObjectTypeEnum.IMM_OR_OWNED_OBJECT.getObjectType();

    @JsonProperty("objectId")
    private String objectId;

    private BigInteger version;

    private String digest;

    public ImmOrOwnedObject() {
        this.type = TypeEnum.OBJECT.getType();
    }
}
