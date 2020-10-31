package io.ppatierno.devday2020;

public class EventHubConsumerConfig {

    public final static String CONSUMER_GROUP_ENVVAR = "CONSUMER_GROUP";
    public final static String CONNECTION_STRING_ENVVAR = "CONNECTION_STRING";

    public final static String DEFAULT_CONSUMER_GROUP = "$Default";

    // Endpoint=sb://<eventhubs-namespace>.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<access-key>;EntityPath=my-cluster.devday2020-topic
    private final String connectionString;
    private final String consumerGroup;

    private EventHubConsumerConfig(String connectionString, String consumerGroup) {
        this.connectionString = connectionString;
        this.consumerGroup = consumerGroup;
    }

    public static EventHubConsumerConfig fromEnv() {
        if (System.getenv(CONNECTION_STRING_ENVVAR) == null) {
            throw new IllegalArgumentException("The ConnectionString is mandatory!");
        }
        String connectionString = System.getenv(CONNECTION_STRING_ENVVAR);
        String consumerGroup = System.getenv(CONSUMER_GROUP_ENVVAR) != null ? System.getenv(CONSUMER_GROUP_ENVVAR) : DEFAULT_CONSUMER_GROUP;
        return new EventHubConsumerConfig(connectionString, consumerGroup);
    }

    public String getConnectionString() {
        return connectionString;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }
}
