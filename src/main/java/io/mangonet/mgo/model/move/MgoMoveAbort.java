package io.mangonet.mgo.model.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoMoveAbort {

    @JsonProperty("error_code")
    private Long errorCode;

    private String function;

    private Long line;

    @JsonProperty("module_id")
    private String moduleId;

}
