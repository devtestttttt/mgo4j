package io.mangonet.mgo.model.transaction.kind.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.transaction.kind.ExecutionStatus;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Failure implements ExecutionStatus {

    private String error;

    private final String status = "failure";
}
