package io.mangonet.mgo.model.governance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoValidatorSummary {

    private Long commissionRate;
    private String description;

    private String exchangeRatesId;
    private Long exchangeRatesSize;

    private Long gasPrice;
    private String imageUrl;
    private String name;

    private String netAddress;
    private String networkPubkeyBytes;

    private Long nextEpochCommissionRate;
    private Long nextEpochGasPrice;
    private String nextEpochNetAddress;
    private String nextEpochNetworkPubkeyBytes;
    private String nextEpochP2pAddress;
    private String nextEpochPrimaryAddress;
    private String nextEpochProofOfPossession;
    private String nextEpochProtocolPubkeyBytes;
    private Long nextEpochStake;
    private String nextEpochWorkerAddress;
    private String nextEpochWorkerPubkeyBytes;

    private String operationCapId;
    private String p2pAddress;

    private Long pendingPoolTokenWithdraw;
    private Long pendingStake;
    private Long pendingTotalMgoWithdraw;

    private Long poolTokenBalance;
    private String primaryAddress;

    private String projectUrl;
    private String proofOfPossessionBytes;
    private String protocolPubkeyBytes;

    private Long rewardsPool;

    private Long stakingPoolActivationEpoch;
    private Long stakingPoolDeactivationEpoch;
    private String stakingPoolId;
    private Long stakingPoolMgoBalance;

    private String mgoAddress;
    private Long votingPower;

    private String workerAddress;
    private String workerPubkeyBytes;

}
