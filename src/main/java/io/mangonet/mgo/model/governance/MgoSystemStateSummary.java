package io.mangonet.mgo.model.governance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoSystemStateSummary {

    private List<MgoValidatorSummary> activeValidators = new ArrayList<>();

    private Map<String, Long> atRiskValidators = new HashMap<>();

    private Long epoch;
    private Long epochDurationMs;
    private Long epochStartTimestampMs;

    private String inactivePoolsId;
    private Long inactivePoolsSize;

    private Long maxValidatorCount;
    private Long minValidatorJoiningStake;

    private String pendingActiveValidatorsId;
    private Long pendingActiveValidatorsSize;

    private List<Long> pendingRemovals = new ArrayList<>();

    private Long protocolVersion;
    private Long referenceGasPrice;

    private Boolean safeMode;
    private Long safeModeComputationRewards;
    private Long safeModeNonRefundableStorageFee;
    private Long safeModeStorageRebates;
    private Long safeModeStorageRewards;

    private Long stakeSubsidyBalance;
    private Long stakeSubsidyCurrentDistributionAmount;
    private Integer stakeSubsidyDecreaseRate;
    private Long stakeSubsidyDistributionCounter;
    private Long stakeSubsidyPeriodLength;
    private Long stakeSubsidyStartEpoch;

    private String stakingPoolMappingsId;
    private Long stakingPoolMappingsSize;

    private Long storageFundNonRefundableBalance;
    private Long storageFundTotalObjectStorageRebates;

    private Long systemStateVersion;
    private Long totalStake;

    private String validatorCandidatesId;
    private Long validatorCandidatesSize;

    private Long validatorLowStakeGracePeriod;
    private Long validatorLowStakeThreshold;
    private Long validatorVeryLowStakeThreshold;

    private Map<String, List<String>> validatorReportRecords = new HashMap<>();
}
