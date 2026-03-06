package io.mangonet.mgo.model.transaction.kind.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.transaction.kind.MgoEndOfEpochTransactionKind;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EndOfEpochTransaction extends TransactionBlockKind {

    private List<MgoEndOfEpochTransactionKind> transactions;

    public EndOfEpochTransaction() {
        this.kind = "EndOfEpochTransaction";
    }
}
