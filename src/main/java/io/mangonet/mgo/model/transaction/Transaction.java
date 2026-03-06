package io.mangonet.mgo.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    private String txBytes;

    private List<String> signatures;

    private TransactionBlockResponseOptions options = TransactionBlockResponseOptions.allTrue();

    private String requestType = ExecuteTransactionRequestType.WAIT_FOR_EFFECTS_CERT.getType();

}
