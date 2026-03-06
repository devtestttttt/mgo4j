package io.mangonet.mgo.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageForEventAndEventId {

    private List<Event> data;

    private Boolean hasNextPage;

    private EventId nextCursor;

}
