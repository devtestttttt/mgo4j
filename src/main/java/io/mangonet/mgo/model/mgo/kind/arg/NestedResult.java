package io.mangonet.mgo.model.mgo.kind.arg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.mgo.kind.MgoArgument;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NestedResult extends MgoArgument {

    @JsonProperty("NestedResult")
    private List<Integer> indices; // [uint16, uint16]

    public NestedResult() {
        this.type = "NestedResult";
    }
}