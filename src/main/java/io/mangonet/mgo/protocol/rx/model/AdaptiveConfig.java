package io.mangonet.mgo.protocol.rx.model;

public record AdaptiveConfig(double minSuccessRate, double targetSuccessRate,
                             long maxExecutionTime) {
    public AdaptiveConfig {
        // Parameter validation
        if (minSuccessRate < 0 || minSuccessRate > 100) {
            throw new IllegalArgumentException("minSuccessRate must be between 0 and 100");
        }
    }

    /**
     * Default configuration: Minimum success rate 98%, Target success rate 98%, Maximum execution time 2 seconds
     * @return
     */
    public static AdaptiveConfig defaults() {
        return new AdaptiveConfig(98.0, 99.9, 2000L);
    }
}