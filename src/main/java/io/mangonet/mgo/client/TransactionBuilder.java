package io.mangonet.mgo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.mangonet.mgo.bcs.BcsRegistry;
import io.mangonet.mgo.bcs.types.arg.call.CallArgObjectArg;
import io.mangonet.mgo.bcs.types.arg.object.ObjectArgImmOrOwnedObject;
import io.mangonet.mgo.bcs.types.arg.object.ObjectArgSharedObject;
import io.mangonet.mgo.bcs.types.arg.object.SharedObjectRef;
import io.mangonet.mgo.bcs.types.gas.GasData;
import io.mangonet.mgo.bcs.types.gas.MgoObjectRef;
import io.mangonet.mgo.bcs.types.transaction.*;
import io.mangonet.mgo.bcs.types.transaction.*;
import io.mangonet.mgo.crypto.MgoKeyPair;
import io.mangonet.mgo.crypto.signature.SignatureScheme;
import io.mangonet.mgo.model.Request;
import io.mangonet.mgo.model.object.ObjectData;
import io.mangonet.mgo.model.object.kind.owner.Shared;
import io.mangonet.mgo.model.transaction.MgoTransactionBlockResponse;
import io.mangonet.mgo.model.transaction.Transaction;
import io.mangonet.mgo.model.transaction.TransactionBlockResponseOptions;
import io.mangonet.mgo.protocol.MgoClient;
import io.mangonet.mgo.protocol.exceptions.RpcRequestFailedException;
import io.mangonet.mgo.protocol.http.response.MgoTransactionBlockResponseWrapper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class TransactionBuilder {

    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

    /**
     * Build SharedObject
     * @param mgoClient
     * @param objectId
     * @param mutable
     * @return
     */
    public static CallArgObjectArg buildSharedObject(MgoClient mgoClient, String objectId, boolean mutable) {
        ObjectData objectData = QueryBuilder.getObjectData(mgoClient, objectId);
        return new CallArgObjectArg(new ObjectArgSharedObject(new SharedObjectRef(
                objectId, ((Shared)objectData.getOwner()).getInitialSharedVersion().longValue(), mutable)));
    }

    /**
     * Build SharedObject
     * @param objectId
     * @param version
     * @param mutable
     * @return
     */
    public static CallArgObjectArg buildSharedObject(String objectId, long version, boolean mutable) {
        return new CallArgObjectArg(new ObjectArgSharedObject(new SharedObjectRef(
                objectId, version, mutable)));
    }

    /**
     * Build ImmOrOwnedObject
     * @param objectId
     * @param version
     * @param digest
     * @return
     */
    public static CallArgObjectArg buildImmOrOwnedObject(String objectId, long version, String digest) {
        return new CallArgObjectArg(new ObjectArgImmOrOwnedObject(new MgoObjectRef(
                objectId, version, digest)));
    }

    /**
     * Build ImmOrOwnedObject
     * @param mgoClient
     * @param objectId
     * @return
     */
    public static CallArgObjectArg buildImmOrOwnedObject(MgoClient mgoClient, String objectId) {
        ObjectData objectData = QueryBuilder.getObjectData(mgoClient, objectId);
        return new CallArgObjectArg(new ObjectArgImmOrOwnedObject(new MgoObjectRef(
                objectId, objectData.getVersion().longValue(), objectData.getDigest())));
    }

    /**
     * Build GasData single gas
     * @param gasObjectId
     * @param sender
     * @param gasPrice
     * @param gasBudget
     * @return
     */
    public static GasData buildGasData(MgoClient mgoClient, String gasObjectId, String sender, long gasPrice, BigInteger gasBudget) {
        ObjectData objectData = QueryBuilder.getObjectData(mgoClient, gasObjectId);
        MgoObjectRef mgoObjectRef = new MgoObjectRef(gasObjectId, objectData.getVersion().longValue(), objectData.getDigest());
        return new GasData(List.of(mgoObjectRef), sender, gasPrice, gasBudget);
    }

    /**
     * Build GasData single gas
     * @param gasObjectId
     * @param version
     * @param digest
     * @param sender
     * @param gasPrice
     * @param gasBudget
     * @return
     */
    public static GasData buildGasData(String gasObjectId, long version, String digest, String sender, long gasPrice, BigInteger gasBudget) {
        MgoObjectRef mgoObjectRef = new MgoObjectRef(gasObjectId, version, digest);
        return new GasData(List.of(mgoObjectRef), sender, gasPrice, gasBudget);
    }

    /**
     * Build GasData single gas
     * @param mgoObjectRef
     * @param sender
     * @param gasPrice
     * @param gasBudget
     * @return
     */
    public static GasData buildGasData(MgoObjectRef mgoObjectRef, String sender, long gasPrice, BigInteger gasBudget) {
        return new GasData(List.of(mgoObjectRef), sender, gasPrice, gasBudget);
    }

    /**
     * Build GasData multiple gas
     * @param gasObjectRefList
     * @param sender
     * @param gasPrice
     * @param gasBudget
     * @return
     */
    public static GasData buildGasData(List<MgoObjectRef> gasObjectRefList, String sender, long gasPrice, BigInteger gasBudget) {
        return new GasData(gasObjectRefList, sender, gasPrice, gasBudget);
    }

    /**
     * Build GasData multiple gas
     * @param mgoClient
     * @param sender
     * @param gasPrice
     * @param gasBudget
     * @return
     */
    public static GasData buildGasData(MgoClient mgoClient, String sender, long gasPrice, BigInteger gasBudget) {
        List<MgoObjectRef> mgoObjectRefs = CoinWithBalance.getMergeGas(mgoClient, sender, gasBudget);
        return new GasData(mgoObjectRefs, sender, gasPrice, gasBudget);
    }

    /**
     * Build GasData multiple gas
     * @param mgoClient
     * @param sender
     * @param gasPrice
     * @param gasBudget
     * @param txMgoUse
     * @return
     */
    public static GasData buildGasData(MgoClient mgoClient, String sender, long gasPrice, BigInteger gasBudget, BigInteger txMgoUse) {
        List<MgoObjectRef> mgoObjectRefs = CoinWithBalance.getMergeGas(mgoClient, sender, gasBudget.add(txMgoUse));
        return new GasData(mgoObjectRefs, sender, gasPrice, gasBudget);
    }

    /**
     * Build TransactionData V1 version
     * @param programmableTx
     * @param sender
     * @param gasData
     * @param transactionExpiration
     * @return
     */
    public static TransactionData buildTransactionDataV1(ProgrammableTransaction programmableTx, String sender, GasData gasData, TransactionExpiration transactionExpiration) {
        TransactionKind programmableKind = new TransactionKind.ProgrammableTransaction(programmableTx);
        TransactionDataV1 transactionDataV1 = new TransactionDataV1(
                programmableKind,
                sender,
                gasData,
                transactionExpiration
        );
        return new TransactionData.V1(transactionDataV1);
    }

    /**
     * Build txBytes (with expiration epoch)
     * @param programmableTx
     * @param sender
     * @param gasData
     * @param epoch
     * @return txBytes
     * @throws IOException
     */
    public static String serializeTransactionBytes(ProgrammableTransaction programmableTx, String sender, GasData gasData, long epoch) throws IOException {
        TransactionData transactionData = buildTransactionDataV1(programmableTx, sender, gasData, new TransactionExpiration.Epoch(epoch));
        return BcsRegistry.serializeToBase64(transactionData, BcsRegistry.TRANSACTION_DATA_SERIALIZER);
    }

    /**
     * Build txBytes
     * @param programmableTx
     * @param sender
     * @param gasData
     * @return txBytes
     * @throws IOException
     */
    public static String serializeTransactionBytes(ProgrammableTransaction programmableTx, String sender, GasData gasData) throws IOException {
        TransactionData transactionData = buildTransactionDataV1(programmableTx, sender, gasData, TransactionExpiration.None.INSTANCE);
        return BcsRegistry.serializeToBase64(transactionData, BcsRegistry.TRANSACTION_DATA_SERIALIZER);
    }

    /**
     * Build tx
     * @param programmableTx
     * @param sender
     * @param gasData
     * @return txBytes
     * @throws IOException
     */
    public static Transaction buildTransaction(ProgrammableTransaction programmableTx, String sender, GasData gasData, MgoKeyPair mgoKeyPair) throws IOException {
        return buildTransaction(serializeTransactionBytes(programmableTx, sender, gasData), mgoKeyPair);
    }

    /**
     * Build tx
     * @param txBytes
     * @param key
     * @param signatureScheme
     * @return
     */
    public static Transaction buildTransaction(String txBytes, String key, SignatureScheme signatureScheme) {
        return buildTransaction(txBytes, MgoKeyPair.decodeHex(key, signatureScheme));
    }

    /**
     * Build tx
     * @param txBytes
     * @param mgoKeyPair
     * @return
     */
    public static Transaction buildTransaction(String txBytes, MgoKeyPair mgoKeyPair) {
        String sign = mgoKeyPair.signTransactionDataBase64(txBytes);
        return buildTransaction(txBytes, List.of(sign));
    }

    /**
     * Build tx
     * @param txBytes
     * @param mgoKeyPair
     * @return
     */
    public static Transaction buildTransaction(String txBytes, MgoKeyPair mgoKeyPair, TransactionBlockResponseOptions options) {
        String sign = mgoKeyPair.signTransactionDataBase64(txBytes);
        return buildTransaction(txBytes, List.of(sign), options);
    }

    /**
     * Build tx
     * @param txBytes
     * @param signature
     * @return
     */
    public static Transaction buildTransaction(String txBytes, String signature) {
        return buildTransaction(txBytes, List.of(signature));
    }

    /**
     * Build tx
     * @param txBytes
     * @param signature
     * @param options
     * @return
     */
    public static Transaction buildTransaction(String txBytes, String signature, TransactionBlockResponseOptions options) {
        return buildTransaction(txBytes, List.of(signature), options);
    }

    /**
     * Build tx
     * @param txBytes
     * @param signatures
     * @return
     */
    public static Transaction buildTransaction(String txBytes, List<String> signatures) {
        // Build Transaction object
        Transaction transaction = new Transaction();
        transaction.setTxBytes(txBytes);
        transaction.setSignatures(signatures);
        return transaction;
    }

    /**
     * Build tx with custom return parameters
     * @param txBytes
     * @param signatures
     * @return
     */
    public static Transaction buildTransaction(String txBytes, List<String> signatures, TransactionBlockResponseOptions options) {
        // Build Transaction object
        Transaction transaction = new Transaction();
        transaction.setTxBytes(txBytes);
        transaction.setSignatures(signatures);
        transaction.setOptions(options);
        return transaction;
    }

    /**
     * Send tx with custom return parameters
     * @param txBytes
     * @param signatures
     * @return
     */
    public static MgoTransactionBlockResponse sendTransaction(MgoClient mgoClient, String txBytes, List<String> signatures, TransactionBlockResponseOptions options) throws IOException {
        if (txBytes == null || txBytes.isEmpty()) {
            throw new IllegalArgumentException("Send tx error, txBytes is empty!");
        }

        Transaction transaction = TransactionBuilder.buildTransaction(txBytes, signatures, options);
        Request<?, MgoTransactionBlockResponseWrapper> tx = mgoClient.executeTransactionBlock(transaction);
        MgoTransactionBlockResponseWrapper send = tx.send();
        return send.getResult();
    }

    /**
     * Send tx with custom return parameters
     * @param mgoClient
     * @param programmableTx
     * @param mgoKeyPair
     * @param gasData
     * @return
     * @throws IOException
     */
    public static MgoTransactionBlockResponse sendTransaction(MgoClient mgoClient, ProgrammableTransaction programmableTx, MgoKeyPair mgoKeyPair, GasData gasData) throws IOException {
        String address = mgoKeyPair.address();
        String txBytes;
        try {
            txBytes = TransactionBuilder.serializeTransactionBytes(programmableTx, address, gasData);
        } catch (IOException e) {
            throw new RpcRequestFailedException("Send tx failed! Cause : " + e.getMessage());
        }
        Transaction transaction = TransactionBuilder.buildTransaction(txBytes, mgoKeyPair);
        Request<?, MgoTransactionBlockResponseWrapper> tx = mgoClient.executeTransactionBlock(transaction);
        MgoTransactionBlockResponseWrapper send = tx.send();
        return send.getResult();
    }

    /**
     * Send tx with custom return parameters
     * @param mgoClient
     * @param programmableTx
     * @param mgoKeyPair
     * @param gasData
     * @return
     * @throws IOException
     */
    public static MgoTransactionBlockResponse sendTransaction(MgoClient mgoClient, ProgrammableTransaction programmableTx, MgoKeyPair mgoKeyPair, GasData gasData, TransactionBlockResponseOptions options) throws IOException {
        String address = mgoKeyPair.address();
        String txBytes;
        try {
            txBytes = TransactionBuilder.serializeTransactionBytes(programmableTx, address, gasData);
        } catch (IOException e) {
            throw new RpcRequestFailedException("Send tx failed! Cause : " + e.getMessage());
        }
        Transaction transaction = TransactionBuilder.buildTransaction(txBytes, mgoKeyPair, options);
        Request<?, MgoTransactionBlockResponseWrapper> tx = mgoClient.executeTransactionBlock(transaction);
        MgoTransactionBlockResponseWrapper send = tx.send();
        return send.getResult();
    }

    /**
     * Send tx with custom return parameters
     * @param mgoClient
     * @param programmableTx
     * @param mgoKeyPair
     * @param gasPrice
     * @param gasBudget
     * @return
     * @throws IOException
     */
    public static MgoTransactionBlockResponse sendTransaction(MgoClient mgoClient, ProgrammableTransaction programmableTx, MgoKeyPair mgoKeyPair, long gasPrice, BigInteger gasBudget) throws IOException {
        String address = mgoKeyPair.address();
        String txBytes;
        try {
            txBytes = TransactionBuilder.serializeTransactionBytes(programmableTx, address, buildGasData(mgoClient, address, gasPrice, gasBudget));
        } catch (IOException e) {
            throw new RpcRequestFailedException("Send tx failed! Cause : " + e.getMessage());
        }
        Transaction transaction = TransactionBuilder.buildTransaction(txBytes, mgoKeyPair);
        Request<?, MgoTransactionBlockResponseWrapper> tx = mgoClient.executeTransactionBlock(transaction);
        MgoTransactionBlockResponseWrapper send = tx.send();
        return send.getResult();
    }

    /**
     * Send tx with custom return parameters
     * @param mgoClient
     * @param programmableTx
     * @param mgoKeyPair
     * @param gasPrice
     * @param gasBudget
     * @param options
     * @return
     * @throws IOException
     */
    public static MgoTransactionBlockResponse sendTransaction(MgoClient mgoClient, ProgrammableTransaction programmableTx, MgoKeyPair mgoKeyPair, long gasPrice, BigInteger gasBudget, TransactionBlockResponseOptions options) throws IOException {
        String address = mgoKeyPair.address();
        String txBytes;
        try {
            txBytes = TransactionBuilder.serializeTransactionBytes(programmableTx, address, buildGasData(mgoClient, address, gasPrice, gasBudget));
        } catch (IOException e) {
            throw new RpcRequestFailedException("Send tx failed! Cause : " + e.getMessage());
        }
        Transaction transaction = TransactionBuilder.buildTransaction(txBytes, mgoKeyPair, options);
        Request<?, MgoTransactionBlockResponseWrapper> tx = mgoClient.executeTransactionBlock(transaction);
        MgoTransactionBlockResponseWrapper send = tx.send();
        return send.getResult();
    }

    /**
     * Send tx with custom return parameters
     * @param txBytes
     * @param signatures
     * @return
     */
    public static MgoTransactionBlockResponse sendTransaction(MgoClient mgoClient, String txBytes, List<String> signatures) throws IOException {
        if (txBytes == null || txBytes.isEmpty()) {
            throw new IllegalArgumentException("Send tx error, txBytes is empty!");
        }

        Transaction transaction = TransactionBuilder.buildTransaction(txBytes, signatures);
        Request<?, MgoTransactionBlockResponseWrapper> tx = mgoClient.executeTransactionBlock(transaction);
        MgoTransactionBlockResponseWrapper send = tx.send();
        return send.getResult();
    }
}
