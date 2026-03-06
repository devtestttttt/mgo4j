package io.mangonet.mgo.model.transaction;

import lombok.Data;

import java.util.List;

@Data
public class TransactionBlock {

    private TransactionBlockData data;

    private List<String> txSignatures;

}
