package io.ppatierno.devday2020;

import com.azure.messaging.eventhubs.EventProcessorClient;
import com.azure.messaging.eventhubs.EventProcessorClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class EventHubConsumer {

    private static final Logger log = LogManager.getLogger(EventHubConsumer.class);

    public static void main(String[] args) throws Exception {

        log.info("EventHubConsumer starting ...");
        EventProcessorClient eventProcessorClient = new EventProcessorClientBuilder()
                .consumerGroup("$Default")
                .connectionString("Endpoint=sb://<eventhubs-namespace>.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<access-key>;EntityPath=my-cluster.devday2020-topic")
                .checkpointStore(new SampleCheckpointStore())
                .processEvent(eventContext -> {
                    log.info("Message: value={}, partitionId={}, sequenceNr={}",
                            eventContext.getEventData().getBodyAsString(),
                            eventContext.getPartitionContext().getPartitionId(),
                            eventContext.getEventData().getSequenceNumber());
                })
                .processError(errorContext -> {
                    log.error("Error occurred while processing events", errorContext.getThrowable());
                })
                .buildEventProcessorClient();

        // This will start the processor. It will start processing events from all partitions.
        eventProcessorClient.start();
        log.info("EventHubConsumer started!");

        // (for demo purposes only - adding sleep to wait for receiving events)
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);

        // This will stop processing events.
        eventProcessorClient.stop();
    }
}
