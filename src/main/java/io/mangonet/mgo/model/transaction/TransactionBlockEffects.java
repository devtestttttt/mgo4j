package io.mangonet.mgo.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.gas.GasCostSummary;
import io.mangonet.mgo.model.move.MgoMoveAbort;
import io.mangonet.mgo.model.object.ObjectRef;
import io.mangonet.mgo.model.object.OwnedObjectRef;
import io.mangonet.mgo.model.transaction.kind.ExecutionStatus;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionBlockEffects {

    private MgoMoveAbort abortError;

    private List<OwnedObjectRef> created;

    private List<ObjectRef> deleted;

    private List<String> dependencies;

    private String eventsDigest;

    private BigInteger executedEpoch;

    private OwnedObjectRef gasObject;

    private GasCostSummary gasUsed;

    private final String messageVersion = "v1";

    private List<TransactionBlockEffectsModifiedAtVersions> modifiedAtVersions;

    private List<OwnedObjectRef> mutated;

    private List<ObjectRef> sharedObjects;

    private ExecutionStatus status;

    private String transactionDigest;

    private List<OwnedObjectRef> unwrapped;

    private List<ObjectRef> unwrappedThenDeleted;

    private List<ObjectRef> wrapped;

}
