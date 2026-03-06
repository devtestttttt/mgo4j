package io.mangonet.mgo.protocol.http.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetMoveFunctionArgTypes {

    @JsonProperty("package")
    private String objectId;

    private String module;

    private String function;

}
