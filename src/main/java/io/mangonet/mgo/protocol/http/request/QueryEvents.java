package io.mangonet.mgo.protocol.http.request;

import io.mangonet.mgo.model.event.EventId;
import io.mangonet.mgo.model.filter.EventFilter;
import lombok.Data;

@Data
public class QueryEvents {

    private EventFilter query;

    private EventId cursor;

    private Long limit;

    private Boolean descendingOrder;

}
