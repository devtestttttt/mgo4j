package io.mangonet.mgo.model.transaction.kind.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomnessStateUpdate extends TransactionBlockKind {

    private BigInteger epoch;

    @JsonProperty("random_bytes")
    private List<Integer> randomBytes;

    @JsonProperty("randomness_round")
    private BigInteger randomnessRound;

    public RandomnessStateUpdate() {
        this.kind = "RandomnessStateUpdate";
    }
}