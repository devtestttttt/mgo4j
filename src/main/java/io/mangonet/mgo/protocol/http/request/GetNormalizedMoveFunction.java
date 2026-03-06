package io.mangonet.mgo.protocol.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetNormalizedMoveFunction {

    @JsonProperty("package")
    private String objectId;

    @JsonProperty("module_name")
    private String moduleName;

    @JsonProperty("function_name")
    private String functionName;

}
