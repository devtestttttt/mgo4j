package io.mangonet.mgo.model.mgo.kind.call;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.mgo.kind.MgoCallArg;
import io.mangonet.mgo.model.mgo.kind.call.enums.ObjectTypeEnum;
import io.mangonet.mgo.model.mgo.kind.call.enums.TypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SharedObject extends MgoCallArg {

    @JsonProperty("objectType")
    private String objectType = ObjectTypeEnum.SHARED_OBJECT.getObjectType();

    @JsonProperty("objectId")
    private String objectId;

    @JsonProperty("initialSharedVersion")
    private BigInteger initialSharedVersion; // SequenceNumber -> BigInteger

    private boolean mutable;

    public SharedObject() {
        this.type = TypeEnum.OBJECT.getType();
    }
}