package io.mangonet.mgo.protocol.core;

import io.mangonet.mgo.model.Request;
import io.mangonet.mgo.model.event.Event;
import io.mangonet.mgo.model.read.ChainIdentifier;
import io.mangonet.mgo.model.transaction.Transaction;
import io.mangonet.mgo.protocol.MgoClient;
import io.mangonet.mgo.protocol.MgoService;
import io.mangonet.mgo.protocol.http.request.*;
import io.mangonet.mgo.protocol.http.response.*;
import io.mangonet.mgo.protocol.http.request.*;
import io.mangonet.mgo.protocol.http.response.*;
import io.mangonet.mgo.protocol.rx.AdaptivePollingManager;
import io.mangonet.mgo.protocol.rx.Callback;
import io.mangonet.mgo.protocol.rx.EventQueryTask;
import io.mangonet.mgo.protocol.rx.JsonRpcMgoPolling;
import io.mangonet.mgo.protocol.rx.model.AdaptiveConfig;
import io.mangonet.mgo.util.Async;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class JsonRpcMgo implements MgoClient {

    public static final int DEFAULT_BLOCK_TIME = 5 * 100;
//    private static final BigInteger MIN_BLOB_BASE_FEE = new BigInteger("1");
//    private static final BigInteger BLOB_BASE_FEE_UPDATE_FRACTION = new BigInteger("3338477");

    protected final MgoService mgoService;
    private final JsonRpcMgoPolling jsonRpcMgoPolling;
    private final AdaptivePollingManager adaptivePollingManager;
    private final long blockTime;
    private final ScheduledExecutorService scheduledExecutorService;

    public JsonRpcMgo(MgoService mgoService) {
        this(mgoService, DEFAULT_BLOCK_TIME, Async.defaultExecutorService());
    }

    public JsonRpcMgo(
            MgoService mgoService,
            long pollingInterval,
            ScheduledExecutorService scheduledExecutorService) {
        this.mgoService = mgoService;
        this.jsonRpcMgoPolling = new JsonRpcMgoPolling(scheduledExecutorService);
        this.adaptivePollingManager = new AdaptivePollingManager(jsonRpcMgoPolling);
        this.blockTime = pollingInterval;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    // --------------------- Coin Query API start ---------------------

    @Override
    public Request<?, BalanceWrapper> getBalance(GetBalance request) {
        return new Request<>(
                "mgox_getBalance",
                Arrays.asList(request.getOwner(), request.getCoinType()),
                mgoService,
                BalanceWrapper.class);
    }

    @Override
    public Request<?, PageForCoinAndStringWrapper> getCoins(GetCoins request) {
        return new Request<>(
                "mgox_getCoins",
                Arrays.asList(request.getOwner(), request.getCoinType(), request.getCursor(), request.getLimit()),
                mgoService,
                PageForCoinAndStringWrapper.class);
    }

    // --------------------- Coin Query API end ---------------------

    // --------------------- Extended API start ---------------------

    @Override
    public Request<?, MgoObjectResponseWrapper> getDynamicFieldObject(GetDynamicFieldObject request) {
        return new Request<>(
                "mgox_getDynamicFieldObject",
                List.of(request.getParentObjectId(), request.getName()),
                mgoService,
                MgoObjectResponseWrapper.class);
    }

    @Override
    public Request<?, PageForMgoObjectResponseAndObjectIdWrapper> getOwnedObjects(GetOwnedObjects request) {
        return new Request<>(
                "mgox_getOwnedObjects",
                Arrays.asList(request.getAddress(), request.getQuery(), request.getCursor(), request.getLimit()),
                mgoService,
                PageForMgoObjectResponseAndObjectIdWrapper.class);
    }

    @Override
    public Request<?, PageForEventAndEventIdWrapper> queryEvents(QueryEvents request) {
        return new Request<>(
                "mgox_queryEvents",
                Arrays.asList(request.getQuery(), request.getCursor(), request.getLimit(), request.getDescendingOrder()),
                mgoService,
                PageForEventAndEventIdWrapper.class);
    }

    // --------------------- Extended API end ---------------------

    // --------------------- Governance Read API start ---------------------

    @Override
    public Request<?, MgoSystemStateWrapper> getLatestMgoSystemState() {
        return new Request<>(
                "mgox_getLatestMgoSystemState",
                Collections.<String>emptyList(),
                mgoService,
                MgoSystemStateWrapper.class);
    }

    @Override
    public Request<?, GasPriceWrapper> getReferenceGasPrice() {
        return new Request<>(
                "mgox_getReferenceGasPrice",
                Collections.<String>emptyList(),
                mgoService,
                GasPriceWrapper.class);
    }

    // --------------------- Governance Read API start ---------------------

    // --------------------- Move Utils API start ---------------------

    @Override
    public Request<?, MoveFunctionArgTypesWrapper> getMoveFunctionArgTypes(GetMoveFunctionArgTypes request) {

        return new Request<>(
                "mgo_getMoveFunctionArgTypes",
                List.of(request.getObjectId(), request.getModule(), request.getFunction()),
                mgoService,
                MoveFunctionArgTypesWrapper.class);
    }

    @Override
    public Request<?, MgoMoveNormalizedFunctionWrapper> getNormalizedMoveFunction(GetNormalizedMoveFunction request) {
        return new Request<>(
                "mgo_getNormalizedMoveFunction",
                List.of(request.getObjectId(), request.getModuleName(), request.getFunctionName()),
                mgoService,
                MgoMoveNormalizedFunctionWrapper.class);
    }

    @Override
    public Request<?, MgoMoveNormalizedModuleWrapper> getNormalizedMoveModule(GetNormalizedMoveModule request) {
        return new Request<>(
                "mgo_getNormalizedMoveModule",
                List.of(request.getObjectId(), request.getModuleName()),
                mgoService,
                MgoMoveNormalizedModuleWrapper.class);
    }

    @Override
    public Request<?, MgoMoveNormalizedModuleByPackageWrapper> getNormalizedMoveModulesByPackage(GetNormalizedMoveModuleByPackage request) {
        return new Request<>(
                "mgo_getNormalizedMoveModulesByPackage",
                List.of(request.getObjectId()),
                mgoService,
                MgoMoveNormalizedModuleByPackageWrapper.class);
    }

    @Override
    public Request<?, MgoMoveNormalizedStructWrapper> getNormalizedMoveStruct(GetNormalizedMoveStruct request) {
        return new Request<>(
                "mgo_getNormalizedMoveStruct",
                List.of(request.getObjectId(), request.getModuleName(), request.getStructName()),
                mgoService,
                MgoMoveNormalizedStructWrapper.class);
    }

    // --------------------- Move Utils API end ---------------------

    // --------------------- Read API start ---------------------

    @Override
    public Request<?, ChainIdentifier> getChainIdentifier() {
        return new Request<>(
                "mgo_getChainIdentifier",
                Collections.<String>emptyList(),
                mgoService,
                ChainIdentifier.class);
    }

    @Override
    public Request<?, CheckpointSequenceNumberWrapper> getLatestCheckpointSequenceNumber() {
        return new Request<>(
                "mgo_getLatestCheckpointSequenceNumber",
                Collections.<String>emptyList(),
                mgoService,
                CheckpointSequenceNumberWrapper.class);
    }

    @Override
    public Request<?, MgoObjectResponseWrapper> getObject(GetObject request) {
        return new Request<>(
                "mgo_getObject",
                Arrays.asList(request.getObjectId(), request.getOptions()),
                mgoService,
                MgoObjectResponseWrapper.class);
    }

    @Override
    public Request<?, TotalTransactionBlocksWrapper> getTotalTransactionBlocks() {
        return new Request<>(
                "mgo_getTotalTransactionBlocks",
                Collections.<String>emptyList(),
                mgoService,
                TotalTransactionBlocksWrapper.class);
    }

    @Override
    public Request<?, MgoMultiObjectResponseWrapper> multiGetObjects(MultiGetObjects request) {
        return new Request<>(
                "mgo_multiGetObjects",
                Arrays.asList(request.getObjectIds(), request.getOptions()),
                mgoService,
                MgoMultiObjectResponseWrapper.class);
    }

    @Override
    public Request<?, ZkLoginVerifyResultWrapper> verifyZkLoginSignature(VerifyZkLoginSignature request) {
        return new Request<>(
                "mgo_verifyZkLoginSignature",
                List.of(request.getBytes(), request.getSignature(), request.getIntentScope(), request.getAuthor()),
                mgoService,
                ZkLoginVerifyResultWrapper.class);
    }

    // --------------------- Read API end ---------------------

    // --------------------- Transaction Builder API start ---------------------

    @Override
    public Request<?, TransactionBlockBytesWrapper> batchTransaction(UnsafeBatchTransaction request) {
        return new Request<>(
                "unsafe_batchTransaction",
                Arrays.asList(request.getSigner(), request.getSingleTransactionParams(), request.getGas(), request.getGasBudget(), request.getExecutionMode()),
                mgoService,
                TransactionBlockBytesWrapper.class);
    }

    @Override
    public Request<?, TransactionBlockBytesWrapper> moveCall(UnsafeMoveCall request) {
        return new Request<>(
                "unsafe_moveCall",
                Arrays.asList(request.getSigner(), request.getPackageObjectId(), request.getModule(), request.getFunction(), request.getTypeArguments(), request.getArguments(), request.getGas(), request.getGasBudget(), request.getExecutionMode()),
                mgoService,
                TransactionBlockBytesWrapper.class);
    }

    @Override
    public Request<?, TransactionBlockBytesWrapper> pay(UnsafePay request) {
        return new Request<>(
                "unsafe_pay",
                Arrays.asList(request.getSigner(), request.getInputCoins(), request.getRecipients(), request.getAmounts(), request.getGas(), request.getGasBudget()),
                mgoService,
                TransactionBlockBytesWrapper.class);
    }

    @Override
    public Request<?, TransactionBlockBytesWrapper> payMgo(UnsafePayMgo request) {
        return new Request<>(
                "unsafe_payMgo",
                Arrays.asList(request.getSigner(), request.getInputCoins(), request.getRecipients(), request.getAmounts(), request.getGasBudget()),
                mgoService,
                TransactionBlockBytesWrapper.class);
    }

    @Override
    public Request<?, TransactionBlockBytesWrapper> payAllMgo(UnsafePayAllMgo request) {
        return new Request<>(
                "unsafe_payAllMgo",
                Arrays.asList(request.getSigner(), request.getInputCoins(), request.getRecipient(), request.getGasBudget()),
                mgoService,
                TransactionBlockBytesWrapper.class);
    }

    @Override
    public Request<?, TransactionBlockBytesWrapper> transferObject(UnsafeTransferObject request) {
        return new Request<>(
                "unsafe_transferObject",
                Arrays.asList(request.getSigner(), request.getObjectId(), request.getGas(), request.getGasBudget(), request.getRecipient()),
                mgoService,
                TransactionBlockBytesWrapper.class);
    }

    // --------------------- Transaction Builder API end ---------------------

    // --------------------- Write API start ---------------------

    @Override
    public Request<?, MgoTransactionBlockResponseWrapper> executeTransactionBlock(Transaction request) {
        return new Request<>(
                "mgo_executeTransactionBlock",
                Arrays.asList(request.getTxBytes(), request.getSignatures(), request.getOptions(), request.getRequestType()),
                mgoService,
                MgoTransactionBlockResponseWrapper.class);
    }

    // --------------------- Write API end ---------------------

    // --------------------- polling API start ---------------------

    @Override
    public void shutdown() {
        scheduledExecutorService.shutdown();
        try {
            mgoService.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to close web3j service", e);
        }
    }

    @Override
    public String mgoEventSubscribe(QueryEvents request, Callback<List<Event>> callback) {
        return mgoEventSubscribe(request, callback, blockTime);
    }

    @Override
    public String mgoEventSubscribe(QueryEvents request, Callback<List<Event>> callback, long interval) {
        EventQueryTask eventTask = new EventQueryTask(this, request, callback);
        String taskId = "event-query" + eventTask.hashCode();
        adaptivePollingManager.registerAdaptiveTask(
                taskId,
                eventTask,
                interval,
                AdaptiveConfig.defaults()
        );
        return taskId;
    }

    @Override
    public boolean unSubscribe(String taskId) {
        return jsonRpcMgoPolling.stopTask(taskId);
    }

    // --------------------- polling API end ---------------------

    @Override
    public void close() throws Exception {
        this.shutdown();
    }

}
