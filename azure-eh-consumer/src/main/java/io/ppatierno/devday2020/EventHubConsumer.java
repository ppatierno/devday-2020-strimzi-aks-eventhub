package io.ppatierno.devday2020;

import com.azure.messaging.eventhubs.EventProcessorClient;
import com.azure.messaging.eventhubs.EventProcessorClientBuilder;

import java.util.concurrent.TimeUnit;

public class EventHubConsumer {

    public static void main(String[] args) throws Exception {

        EventProcessorClient eventProcessorClient = new EventProcessorClientBuilder()
                .consumerGroup("$Default")
                .connectionString("Endpoint=sb://<eventhubs-namespace>.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<access-key>;EntityPath=my-cluster.devday2020-topic")
                .checkpointStore(new SampleCheckpointStore())
                .processEvent(eventContext -> {
                    System.out.println("Got message: value=" + eventContext.getEventData().getBodyAsString() +
                            ", partitionId=" + eventContext.getPartitionContext().getPartitionId() +
                            ", sequenceNr=" + eventContext.getEventData().getSequenceNumber());
                })
                .processError(errorContext -> {
                    System.out
                            .println("Error occurred while processing events " + errorContext.getThrowable().getMessage());
                })
                .buildEventProcessorClient();

        // This will start the processor. It will start processing events from all partitions.
        eventProcessorClient.start();

        // (for demo purposes only - adding sleep to wait for receiving events)
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);

        // This will stop processing events.
        eventProcessorClient.stop();
    }
}
