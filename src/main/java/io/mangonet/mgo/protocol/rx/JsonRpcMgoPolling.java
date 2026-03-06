package io.mangonet.mgo.protocol.rx;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

@Slf4j
public class JsonRpcMgoPolling implements AutoCloseable{

    private final ScheduledExecutorService scheduler;
    private final Map<String, PollingTask<?>> tasks = new ConcurrentHashMap<>();
    private final boolean useVirtualThreads;

    public JsonRpcMgoPolling(boolean useVirtualThreads) {
        this.useVirtualThreads = useVirtualThreads;
        this.scheduler = createScheduler();
    }

    public JsonRpcMgoPolling(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
        this.useVirtualThreads = true;
    }

    public JsonRpcMgoPolling() {
        this(true); // Default to using virtual threads.
    }

    private ScheduledExecutorService createScheduler() {
        if (useVirtualThreads) {
            return Executors.newScheduledThreadPool(1,
                    Thread.ofVirtual().factory());
        } else {
            return Executors.newScheduledThreadPool(4); // Platform thread pool.
        }
    }

    public <T> void registerTask(String taskId, PollingTask<T> task) {
        tasks.put(taskId, task);
        log.info("Registered polling task: {}", taskId);
    }

    public void startAll(long intervalMs) {
        tasks.forEach((taskId, task) -> {
            startTask(taskId, task, intervalMs);
        });
        log.info("Started {} polling tasks with interval {}ms", tasks.size(), intervalMs);
    }

    public <T> void startTask(String taskId, PollingTask<T> task, long intervalMs) {
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(
                () -> executeTaskSafely(task),
                0, intervalMs, TimeUnit.MILLISECONDS
        );

        task.setFuture(future);
        log.debug("Started task: {} with interval {}ms", taskId, intervalMs);
    }

    private void executeTaskSafely(PollingTask<?> task) {
        try {
            task.execute();
        } catch (Exception e) {
            // Retry or alert logic can be added.
            task.onError(e);
        }
    }

    public boolean stopTask(String taskId) {
        PollingTask<?> task = tasks.get(taskId);
        if (task != null && task.getFuture() != null) {
            log.info("Stopped polling task: {}", taskId);
            return task.getFuture().cancel(false);
        }
        return true;
    }

    public void adjustInterval(String taskId, long newIntervalMs) {
        stopTask(taskId);
        PollingTask<?> task = tasks.get(taskId);
        if (task != null) {
            startTask(taskId, task, newIntervalMs);
            log.info("Adjusted interval for task {} to {}ms", taskId, newIntervalMs);
        }
    }

    @Override
    public void close() {
        // Stop all tasks.
        tasks.keySet().forEach(this::stopTask);

        // Shut down the scheduler.
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        log.info("Polling scheduler closed");
    }
}
