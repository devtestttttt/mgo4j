package io.mangonet.mgo.protocol.rx;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.event.Event;
import io.mangonet.mgo.model.event.EventId;
import io.mangonet.mgo.model.event.PageForEventAndEventId;
import io.mangonet.mgo.protocol.MgoClient;
import io.mangonet.mgo.protocol.exceptions.RpcRequestFailedException;
import io.mangonet.mgo.protocol.http.request.QueryEvents;
import io.mangonet.mgo.protocol.http.response.PageForEventAndEventIdWrapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class EventQueryTask extends PollingTask<List<Event>> {

    private final QueryEvents query;
    private final AtomicReference<EventId> lastCursor;

    public EventQueryTask(MgoClient mgoClient,
                          QueryEvents query,
                          Callback<List<Event>> callback) {
        super(mgoClient, callback);
        this.query = query;
        this.lastCursor = new AtomicReference<>(query.getCursor());
    }

    @Override
    public void execute() {
        long startTime = System.currentTimeMillis();

        try {
            // Set next query parameters.
            if (query.getCursor() != null) {
                query.setCursor(lastCursor.get());
            }

            // Execute query.
            PageForEventAndEventIdWrapper resultWrapper = mgoClient.queryEvents(query)
                    .send();
            Response.Error error = resultWrapper.getError();

            if (error != null) {
                throw new RpcRequestFailedException(error.getMessage());
            }
            PageForEventAndEventId result = resultWrapper.getResult();

            // Callback processing result.
            List<Event> data = result.getData();
            if (callback != null && data != null && !data.isEmpty()) {

                // Update cursor.
                if (query.getCursor() != null) {
                    callback.onEvent(data);
                    if (result.getHasNextPage()) {
                        // Query in ascending order by cursor.
                        lastCursor.set(result.getNextCursor());
                    } else {
                        // Query in descending order by cursor.
                        lastCursor.set(data.getLast().getId());
                    }
                } else {
                    // If the cursor is null, there is no need to set a cursor. Save the latest cursor and perform data deduplication.
                    EventId lastEventId = lastCursor.get();
                    if (lastEventId != null) {
                        // Find the position of the last processed event.
                        int lastProcessedIndex = this.findLastProcessedEventIndex(data, lastEventId);

                        if (lastProcessedIndex >= 0) {
                            // Identify duplicate events and extract the unprocessed portion.
                            List<Event> newEvents = data.subList(0, lastProcessedIndex);
                            if (!newEvents.isEmpty()) {
                                // Create a deduplicated result object.
                                callback.onEvent(newEvents);
                                log.debug("Deduplicated events: {} new events, {} duplicates",
                                        newEvents.size(), data.size() - newEvents.size());
                            } else {
                                // All events are duplicates, no processing required.
                                log.debug("All events are duplicates, skipping callback");
                            }
                        } else {
                            // No duplicate events found, process all data.
                            callback.onEvent(data);
                        }
                    } else {
                        // First query, no historical cursor available.
                        callback.onEvent(data);
                    }
                    lastCursor.set(data.getFirst().getId());
                }
            }

            // Update statistics.
            successCount.incrementAndGet();
            lastExecutionTime = System.currentTimeMillis() - startTime;

            log.debug("Event query completed: {} events, cursor: {}",
                    data != null ? data.size() : 0, lastCursor);

        } catch (Exception e) {
            errorCount.incrementAndGet();
            if (callback != null) {
                callback.onError(e);
            }
            throw new RuntimeException("Event query execution failed", e);
        }
    }

    public void resetCursor() {
        this.lastCursor.set(null);
        log.info("Event query cursor reset");
    }

    public EventId getCursorState() {
        return lastCursor.get();
    }

    private int findLastProcessedEventIndex(List<Event> events, EventId lastEventId) {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (this.isSameEvent(event.getId(), lastEventId)) {
                return i; // Return the index of duplicate events.
            }
        }
        return -1; // No duplicate events found.
    }

    private boolean isSameEvent(EventId eventId1, EventId eventId2) {
        if (eventId1 == null || eventId2 == null) {
            return false;
        }

        // Compare transaction digest and event sequence number.
        return Objects.equals(eventId1.getTxDigest(), eventId2.getTxDigest()) &&
                Objects.equals(eventId1.getEventSeq(), eventId2.getEventSeq());
    }
}