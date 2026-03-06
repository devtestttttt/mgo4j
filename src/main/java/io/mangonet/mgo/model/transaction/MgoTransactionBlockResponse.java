package io.mangonet.mgo.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.coin.BalanceChange;
import io.mangonet.mgo.model.event.Event;
import io.mangonet.mgo.model.object.kind.ObjectChange;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoTransactionBlockResponse {

    private List<BalanceChange> balanceChanges;

    private BigInteger checkpoint;

    private Boolean confirmedLocalExecution;

    private String digest;

    private TransactionBlockEffects effects;

    private List<String> errors;

    private List<Event> events;

    private List<ObjectChange> objectChanges;

    private String rawTransaction;

    private BigInteger timestampMs;

    private TransactionBlock transaction;

}
