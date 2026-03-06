package io.mangonet.mgo.model.transaction.kind.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.mgo.kind.MgoCallArg;
import io.mangonet.mgo.model.transaction.kind.MgoTransaction;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgrammableTransaction extends TransactionBlockKind {

    private List<MgoCallArg> inputs;

    private List<MgoTransaction> transactions;

    public ProgrammableTransaction() {
        this.kind = "ProgrammableTransaction";
    }
}
