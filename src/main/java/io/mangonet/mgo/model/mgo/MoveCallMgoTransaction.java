package io.mangonet.mgo.model.mgo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.mgo.kind.MgoArgument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveCallMgoTransaction {

    private List<MgoArgument> arguments;

    private String function;

    private String module;

    private String packageId; // ObjectID

    @JsonProperty("type_arguments")
    private List<String> typeArguments;
}
