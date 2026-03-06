package io.mangonet.mgo.protocol.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.move.MgoTransactionBlockBuilderMode;
import io.mangonet.mgo.model.transaction.kind.RPCTransactionRequestParams;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class UnsafeBatchTransaction {

    private String signer;

    @JsonProperty("single_transaction_params")
    private List<RPCTransactionRequestParams> singleTransactionParams;

    private String gas;

    @JsonProperty("gas_budget")
    private BigInteger gasBudget;

    @JsonProperty("execution_mode")
    private String executionMode = MgoTransactionBlockBuilderMode.COMMIT.getMode();


}
