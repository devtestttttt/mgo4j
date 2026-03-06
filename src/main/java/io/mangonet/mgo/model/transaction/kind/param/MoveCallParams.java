package io.mangonet.mgo.model.transaction.kind.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveCallParams {

    private List<Object> arguments;

    private String function;

    private String module;

    private String packageObjectId;

    private List<String> typeArguments;

}
