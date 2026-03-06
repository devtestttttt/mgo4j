package io.mangonet.mgo.protocol.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.move.MgoTransactionBlockBuilderMode;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class UnsafeMoveCall {

    private String signer;

    @JsonProperty("package_object_id")
    private String packageObjectId;

    private String module;

    private String function;

    @JsonProperty("type_arguments")
    private List<String> typeArguments;

    private List<Object> arguments;

    private String gas;

    @JsonProperty("gas_budget")
    private BigInteger gasBudget;

    @JsonProperty("execution_mode")
    private String executionMode = MgoTransactionBlockBuilderMode.COMMIT.getMode();

}
