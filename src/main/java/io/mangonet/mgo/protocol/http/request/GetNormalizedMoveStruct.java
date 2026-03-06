package io.mangonet.mgo.protocol.http.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetNormalizedMoveStruct {

    @JsonProperty("package")
    private String objectId;

    @JsonProperty("module_name")
    private String moduleName;

    @JsonProperty("struct_name")
    private String structName;

}
