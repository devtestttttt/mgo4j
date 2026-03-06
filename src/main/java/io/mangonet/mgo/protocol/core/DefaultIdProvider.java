package io.mangonet.mgo.protocol.core;

import java.util.concurrent.atomic.AtomicLong;

public class DefaultIdProvider {

    protected static final AtomicLong nextId = new AtomicLong(0);

    protected DefaultIdProvider() {}

    public static long getNextId() {
        return nextId.getAndIncrement();
    }

}
