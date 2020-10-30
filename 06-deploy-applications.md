# Deploy applications

# Run Event Hub consumer

First of all, the Event Hub consumer application has to be built locally using [Maven](https://maven.apache.org/).
Using the Maven Assembly plugin, it is possible to build an executable JAR containing with all the corresponding dependencies.
Just run the following command from the current repository root directory.

```shell
mvn package
```

Finally, start the Event Hub consumer application running the following command.

```shell
java -jar azure-eh-consumer/target/azure-eh-consumer.jar
```

# Deploy Kafka producer

The Kafka producer application has to be deployed on the Kubernetes cluster alongside the Apache Kafka cluster.

 ```shell
kubectl apply -f 06-deploy-applications -n strimzi-demo
```

It starts sending messages with at a fixed rate to the topic.
The messages are mirrored to the Event Hub and the same messages are received by the corresponding Event Hub consumer.

```shell
Got message: value="Hello DevDay 2020!! - 0", partitionId=2, sequenceNr=36
Got message: value="Hello DevDay 2020!! - 1", partitionId=1, sequenceNr=34
Got message: value="Hello DevDay 2020!! - 2", partitionId=2, sequenceNr=37
Got message: value="Hello DevDay 2020!! - 3", partitionId=1, sequenceNr=35
Got message: value="Hello DevDay 2020!! - 4", partitionId=0, sequenceNr=38
Got message: value="Hello DevDay 2020!! - 5", partitionId=1, sequenceNr=36
Got message: value="Hello DevDay 2020!! - 6", partitionId=2, sequenceNr=38
Got message: value="Hello DevDay 2020!! - 7", partitionId=0, sequenceNr=39
Got message: value="Hello DevDay 2020!! - 8", partitionId=2, sequenceNr=39
Got message: value="Hello DevDay 2020!! - 9", partitionId=0, sequenceNr=40
```