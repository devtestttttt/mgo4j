package io.mangonet.mgo.protocol.rx.model;

public record TaskStats(long successCount, long errorCount, long lastExecutionTime) {

    public double getSuccessRate() {
        long total = successCount + errorCount;
        return total > 0 ? (double) successCount / total * 100 : 100.0;
    }

}