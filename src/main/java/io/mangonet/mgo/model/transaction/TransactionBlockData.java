package io.mangonet.mgo.model.transaction;

import io.mangonet.mgo.model.gas.GasData;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import lombok.Data;

@Data
public class TransactionBlockData {

    private GasData gasData;

    private String messageVersion;

    private String sender;

    private TransactionBlockKind transaction;

}
