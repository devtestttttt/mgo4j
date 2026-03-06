package io.mangonet.mgo.model.mgo.kind.arg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.mgo.kind.MgoArgument;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GasCoin extends MgoArgument {

    public GasCoin() {
        this.type = "GasCoin";
    }
}