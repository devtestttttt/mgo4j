package io.mangonet.mgo.protocol;

import io.mangonet.mgo.protocol.core.JsonRpcMgo;
import io.mangonet.mgo.protocol.core.Mgo;
import io.mangonet.mgo.protocol.rx.MgoPolling;

import java.util.concurrent.ScheduledExecutorService;

public interface MgoClient extends Mgo, MgoPolling, AutoCloseable {

    /**
     * Construct a new MgoClient instance.
     *
     * @param mgoService mgo service instance - i.e. HTTP or IPC
     * @return new MgoClient instance
     */
    static MgoClient build(MgoService mgoService) {
        return new JsonRpcMgo(mgoService);
    }

    /**
     * Construct a new MgoClient instance.
     *
     * @param mgoService mgo service instance - i.e. HTTP or IPC
     * @param pollingInterval polling interval for responses from network nodes
     * @param scheduledExecutorService executor service to use for scheduled tasks. <strong>You areresponsible for terminating this thread pool</strong>
     * @return new MgoClient instance
     */
    static MgoClient build(
            MgoService mgoService,
            long pollingInterval,
            ScheduledExecutorService scheduledExecutorService) {
        return new JsonRpcMgo(mgoService, pollingInterval, scheduledExecutorService);
    }

    /** Shutdowns a MgoClient instance and closes opened resources. */
    void shutdown();
}
