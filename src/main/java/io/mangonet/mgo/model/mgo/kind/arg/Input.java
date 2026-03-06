package io.mangonet.mgo.model.mgo.kind.arg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.mgo.kind.MgoArgument;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Input extends MgoArgument {

    @JsonProperty("Input")
    private Integer index; // uint16

    public Input() {
        this.type = "Input";
    }
}
