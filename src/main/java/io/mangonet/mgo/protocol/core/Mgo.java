package io.mangonet.mgo.protocol.core;

import io.mangonet.mgo.model.Request;
import io.mangonet.mgo.model.read.ChainIdentifier;
import io.mangonet.mgo.model.transaction.Transaction;
import io.mangonet.mgo.protocol.http.request.*;
import io.mangonet.mgo.protocol.http.response.*;
import io.mangonet.mgo.protocol.http.request.*;
import io.mangonet.mgo.protocol.http.response.*;

public interface Mgo {

    // --------------------- Coin Query API start ---------------------

    /**
     * Return the total coin balance for one coin type, owned by the address owner
     * @param request
     * @return
     */
    Request<?, BalanceWrapper> getBalance(GetBalance request);

    /**
     * Return all Coin<coin_type> objects owned by an address
     * @param request
     * @return
     */
    Request<?, PageForCoinAndStringWrapper> getCoins(GetCoins request);

    // --------------------- Coin Query API end ---------------------

    // --------------------- Extended API start ---------------------

    /**
     * Return dynamic field object information for specified object
     * @param request
     * @return
     */
    Request<?, MgoObjectResponseWrapper> getDynamicFieldObject(GetDynamicFieldObject request);

    /**
     * Return object list owned by an address
     * @param request
     * @return
     */
    Request<?, PageForMgoObjectResponseAndObjectIdWrapper> getOwnedObjects(GetOwnedObjects request);

    /**
     * Return list of events for a specified query criteria
     * @param request
     * @return
     */
    Request<?, PageForEventAndEventIdWrapper> queryEvents(QueryEvents request);

    // --------------------- Extended API end ---------------------

    // --------------------- Governance Read API start ---------------------

    /**
     * Return latest Mgo system status
     * @return
     */
    Request<?, MgoSystemStateWrapper> getLatestMgoSystemState();

    /**
     * Return network reference gas price
     * @return
     */
    Request<?, GasPriceWrapper> getReferenceGasPrice();

    // --------------------- Governance Read API start ---------------------

    // --------------------- Move Utils API start ---------------------

    /**
     * According to normalized type return Move function parameter type
     * @param request
     * @return
     */
    Request<?, MoveFunctionArgTypesWrapper> getMoveFunctionArgTypes(GetMoveFunctionArgTypes request);

    /**
     * Return structured representation of Move function
     * @param request
     * @return
     */
    Request<?, MgoMoveNormalizedFunctionWrapper> getNormalizedMoveFunction(GetNormalizedMoveFunction request);

    /**
     * Return structured representation of Move module
     * @param request
     * @return
     */
    Request<?, MgoMoveNormalizedModuleWrapper> getNormalizedMoveModule(GetNormalizedMoveModule request);

    /**
     * Return structured representation of modules in given package
     * @param request
     * @return
     */
    Request<?, MgoMoveNormalizedModuleByPackageWrapper> getNormalizedMoveModulesByPackage(GetNormalizedMoveModuleByPackage request);

    /**
     * Return structured representation of Move struct
     * @param request
     * @return
     */
    Request<?, MgoMoveNormalizedStructWrapper> getNormalizedMoveStruct(GetNormalizedMoveStruct request);

    // --------------------- Move Utils API end ---------------------

    // --------------------- READ API start ---------------------

    /**
     * Return first four bytes of chain genesis checkpoint summary
     * @return
     */
    Request<?, ChainIdentifier> getChainIdentifier();

    /**
     * Return the sequence number of the latest checkpoint that has been executed
     * @return
     */
    Request<?, CheckpointSequenceNumberWrapper> getLatestCheckpointSequenceNumber();

    /**
     * Return object information for specified object
     * @param request
     * @return
     */
    Request<?, MgoObjectResponseWrapper> getObject(GetObject request);

    /**
     * Return the total number of transaction blocks known to the server
     * @return
     */
    Request<?, TotalTransactionBlocksWrapper> getTotalTransactionBlocks();

    /**
     * Return object data of object list
     * @param request
     * @return
     */
    Request<?, MgoMultiObjectResponseWrapper> multiGetObjects(MultiGetObjects request);

    /**
     * Verify zklogin signature for given bytes, intent graph range and author
     * @param request
     * @return
     */
    Request<?, ZkLoginVerifyResultWrapper> verifyZkLoginSignature(VerifyZkLoginSignature request);

    // --------------------- READ API end ---------------------

    // --------------------- Transaction Builder API start ---------------------

    /**
     * PTB(ProgramTransactionBlock) interface
     * @param request
     * @return
     */
    Request<?, TransactionBlockBytesWrapper> batchTransaction(UnsafeBatchTransaction request);

    /**
     * Call move interface
     * @param request
     * @return
     */
    Request<?, TransactionBlockBytesWrapper> moveCall(UnsafeMoveCall request);

    /**
     * Transfer token
     * @param request
     * @return
     */
    Request<?, TransactionBlockBytesWrapper> pay(UnsafePay request);

    /**
     * Transfer mgo
     * @param request
     * @return
     */
    Request<?, TransactionBlockBytesWrapper> payMgo(UnsafePayMgo request);


    /**
     * Transfer all mgo
     * @param request
     * @return
     */
    Request<?, TransactionBlockBytesWrapper> payAllMgo(UnsafePayAllMgo request);

    /**
     * Transfer object
     * @param request
     * @return
     */
    Request<?, TransactionBlockBytesWrapper> transferObject(UnsafeTransferObject request);

    // --------------------- Transaction Builder API end ---------------------

    // --------------------- Write API start ---------------------

    /**
     * Execute transaction and wait for result (if needed), request type:
     * 1. WaitForEffectsCert: Wait for TransactionEffectsCert then return to client. This mode is a proxy for transaction finality.
     * 2. WaitForLocalExecution: Wait for TransactionEffectsCert and ensure node has executed transaction locally before returning to client. Local execution ensures that when client triggers subsequent queries, the node can perceive this transaction. However, if node fails to execute transaction locally in time, the boolean type in response will be set to false to indicate this situation. WaitForEffectsCert unless options.show_events or options.show_effects is true, else request_type defaults to true.
     * @param request
     * @return
     */
    Request<?, MgoTransactionBlockResponseWrapper> executeTransactionBlock(Transaction request);

    // --------------------- Write API end ---------------------
}
