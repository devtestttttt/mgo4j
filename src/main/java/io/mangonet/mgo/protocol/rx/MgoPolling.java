package io.mangonet.mgo.protocol.rx;

import io.mangonet.mgo.model.event.Event;
import io.mangonet.mgo.protocol.http.request.QueryEvents;

import java.util.List;

public interface MgoPolling {

    /**
     * Create a polling to filter for specific events on the blockchain.
     * @param request
     * @param callback
     * @return
     */
    String mgoEventSubscribe(QueryEvents request, Callback<List<Event>> callback);

    /**
     * Create a polling to filter for specific events on the blockchain.
     * @param request
     * @param callback
     * @param interval
     * @return
     */
    String mgoEventSubscribe(QueryEvents request, Callback<List<Event>> callback, long interval);

    /**
     * unSubscribe a polling to filter for specific events on the blockchain.
     * @param taskId
     * @return
     */
    boolean unSubscribe(String taskId);
}
