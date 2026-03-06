package io.mangonet.mgo.protocol.rx;

import io.mangonet.mgo.protocol.MgoClient;
import io.mangonet.mgo.protocol.rx.model.TaskStats;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicLong;

public abstract class PollingTask<T> {

    protected volatile ScheduledFuture<?> future;
    protected final MgoClient mgoClient;
    protected final Callback<T> callback;

    // Statistical information.
    protected final AtomicLong successCount = new AtomicLong();
    protected final AtomicLong errorCount = new AtomicLong();
    protected volatile long lastExecutionTime;

    public PollingTask(MgoClient mgoClient, Callback<T> callback) {
        this.mgoClient = mgoClient;
        this.callback = callback;
    }

    /**
     * Execute polling logic.
     */
    public abstract void execute();

    /**
     * Error handling.
     */
    public void onError(Exception e) {
        errorCount.incrementAndGet();
        // Custom error handling logic can be added.
    }

    /**
     * Get task statistics.
     */
    public TaskStats getStats() {
        return new TaskStats(
                successCount.get(),
                errorCount.get(),
                lastExecutionTime
        );
    }

    public void setFuture(ScheduledFuture<?> future) {
        this.future = future;
    }

    public ScheduledFuture<?> getFuture() {
        return future;
    }

}
