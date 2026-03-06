package io.mangonet.mgo.bcs;

import io.mangonet.mgo.bcs.types.effects.ExecutionStatus;
import io.mangonet.mgo.bcs.types.effects.GasCostSummary;
import io.mangonet.mgo.bcs.types.effects.TransactionEffects;
import io.mangonet.mgo.bcs.types.effects.TransactionEffectsV1;
import io.mangonet.mgo.bcs.types.gas.MgoObjectRef;
import org.bitcoinj.core.Base58;
import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.util.List;

public class EffectsBcs {
    
    /**
     * Gas cost summary serializer
     */
    public static final BcsSerializer.BcsTypeSerializer<GasCostSummary> GAS_COST_SUMMARY_SERIALIZER = (serializer, summary) -> {
        serializer.writeU64(summary.getComputationCost());
        serializer.writeU64(summary.getStorageCost());
        serializer.writeU64(summary.getStorageRebate());
        serializer.writeU64(summary.getNonRefundableStorageFee());
    };
    
    /**
     * Transaction effects V1 serializer
     */
    public static final BcsSerializer.BcsTypeSerializer<TransactionEffectsV1> TRANSACTION_EFFECTS_V1_SERIALIZER = (serializer, effects) -> {
        // Serialize execution status
        if (effects.getStatus() == ExecutionStatus.SUCCESS) {
            serializer.writeU32(0); // Success variant
        } else {
            serializer.writeU32(1); // Failed variant
            // Here can add failure status serialization logic
        }
        
        serializer.writeU64(effects.getExecutedEpoch());
        GAS_COST_SUMMARY_SERIALIZER.serialize(serializer, effects.getGasUsed());
        
        // Serialize created objects
        serializer.writeVector(effects.getCreated(), MgoBcs.MGO_OBJECT_REF_SERIALIZER);
        
        // Serialize modified objects
        serializer.writeVector(effects.getMutated(), MgoBcs.MGO_OBJECT_REF_SERIALIZER);
        
        // Serialize deleted objects
        serializer.writeVector(effects.getDeleted(), MgoBcs.MGO_OBJECT_REF_SERIALIZER);
        
        // Serialize transaction digest
        MgoBcs.OBJECT_DIGEST_SERIALIZER.serialize(serializer, effects.getTransactionDigest());
    };
    
    /**
     * Transaction effects serializer
     */
    public static final BcsSerializer.BcsTypeSerializer<TransactionEffects> TRANSACTION_EFFECTS_SERIALIZER = (serializer, effects) -> {
        serializer.writeU32(0); // V1 variant
        TRANSACTION_EFFECTS_V1_SERIALIZER.serialize(serializer, effects.getV1());
    };
    
    /**
     * Serialize transaction effects to Base64
     */
    public static String serializeToBase64(TransactionEffects effects) throws IOException {
        BcsSerializer bcsSerializer = new BcsSerializer();
        TRANSACTION_EFFECTS_SERIALIZER.serialize(bcsSerializer, effects);
        return bcsSerializer.toBase64();
    }
    
    /**
     * Deserialize transaction effects from Base64
     */
    public static TransactionEffects deserializeFromBase64(String base64) throws IOException {
        byte[] data = Base64.decode(base64);
        BcsDeserializer deserializer = new BcsDeserializer(data);
        
        int version = deserializer.readU32();
        if (version == 0) {
            TransactionEffectsV1 v1 = deserializeTransactionEffectsV1(deserializer);
            return new TransactionEffects(v1);
        } else {
            throw new IllegalArgumentException("Unsupported transaction effects version: " + version);
        }
    }
    
    /**
     * Deserialize transaction effects V1
     */
    private static TransactionEffectsV1 deserializeTransactionEffectsV1(BcsDeserializer deserializer) throws IOException {
        // Deserialize execution status
        int statusVariant = deserializer.readU32();
        ExecutionStatus status = statusVariant == 0 ? ExecutionStatus.SUCCESS : ExecutionStatus.FAILED;
        
        long executedEpoch = deserializer.readU64();
        
        // Deserialize gas cost summary
        GasCostSummary gasUsed = deserializeGasCostSummary(deserializer);
        
        // Deserialize created objects
        List<MgoObjectRef> created = deserializer.readVector(d -> {
            try {
                return deserializeMgoObjectRef(d);
            } catch (IOException e) {
                throw new RuntimeException("Failed to deserialize created object", e);
            }
        });
        
        // Deserialize modified objects
        List<MgoObjectRef> mutated = deserializer.readVector(d -> {
            try {
                return deserializeMgoObjectRef(d);
            } catch (IOException e) {
                throw new RuntimeException("Failed to deserialize mutated object", e);
            }
        });
        
        // Deserialize deleted objects
        List<MgoObjectRef> deleted = deserializer.readVector(d -> {
            try {
                return deserializeMgoObjectRef(d);
            } catch (IOException e) {
                throw new RuntimeException("Failed to deserialize deleted object", e);
            }
        });
        
        // Deserialize transaction digest
        String transactionDigest = Base58.encode(deserializer.readBytes());

        return new TransactionEffectsV1(status, executedEpoch, gasUsed, created, mutated, deleted, transactionDigest);
    }
    
    /**
     * Deserialize gas cost summary.
     */
    private static GasCostSummary deserializeGasCostSummary(BcsDeserializer deserializer) throws IOException {
        long computationCost = deserializer.readU64();
        long storageCost = deserializer.readU64();
        long storageRebate = deserializer.readU64();
        long nonRefundableStorageFee = deserializer.readU64();
        
        return new GasCostSummary(computationCost, storageCost, storageRebate, nonRefundableStorageFee);
    }
    
    /**
     * Deserialize MgoObjectRef.
     */
    private static MgoObjectRef deserializeMgoObjectRef(BcsDeserializer deserializer) throws IOException {
        String objectId = MgoBcs.ADDRESS_DESERIALIZER.deserialize(deserializer);
        long version = deserializer.readU64();
        String digest = Base64.toBase64String(deserializer.readBytes());
        
        return new MgoObjectRef(objectId, version, digest);
    }
} 