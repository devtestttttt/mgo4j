package io.mangonet.mgo.model.zk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZkLoginVerifyResult {

    private List<String> errors;

    private Boolean success;

}
