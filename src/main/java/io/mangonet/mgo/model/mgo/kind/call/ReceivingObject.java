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
public class ReceivingObject extends MgoCallArg {

    @JsonProperty("objectType")
    private String objectType = ObjectTypeEnum.RECEIVING.getObjectType();

    @JsonProperty("objectId")
    private String objectId;

    private BigInteger version;

    private String digest;

    public ReceivingObject() {
        this.type = TypeEnum.OBJECT.getType();
    }
}
