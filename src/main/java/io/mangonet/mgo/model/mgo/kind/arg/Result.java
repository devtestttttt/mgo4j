package io.mangonet.mgo.model.mgo.kind.arg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.mgo.kind.MgoArgument;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result extends MgoArgument {

    @JsonProperty("Result")
    private Integer index; // uint16

    public Result() {
        this.type = "Result";
    }
}
