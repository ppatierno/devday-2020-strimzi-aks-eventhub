export CONNECTION_STRING="Endpoint=sb://<eventhubs-namespace>.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<access-key>;EntityPath=my-cluster.devday2020-topic"
java -jar azure-eh-consumer/target/azure-eh-consumer.jar
