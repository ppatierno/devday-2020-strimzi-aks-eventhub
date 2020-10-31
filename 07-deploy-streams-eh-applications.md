# Deploy applications

## Run Event Hub consumer

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

## Deploy Kafka streams application

The Kafka Streams application processes the messages on the internal topic and produce a corresponding message on the mirrored one.
It has to be deployed on the Kubernetes cluster alongside the Apache Kafka cluster.

 ```shell
kubectl apply -f 07-deploy-streams-eh-applications -n strimzi-demo
```

The messages are mirrored to the Event Hub and the same messages are received by the corresponding Event Hub consumer.

```shell
[2020-10-31 18:32:01,326] INFO  <tHubConsumer:24> [dedElastic-3] Message: value="978 - !!0202 yaDveD olleH", partitionId=2, sequenceNr=80
[2020-10-31 18:32:02,332] INFO  <tHubConsumer:24> [dedElastic-5] Message: value="088 - !!0202 yaDveD olleH", partitionId=1, sequenceNr=275
[2020-10-31 18:32:03,359] INFO  <tHubConsumer:24> [dedElastic-6] Message: value="188 - !!0202 yaDveD olleH", partitionId=0, sequenceNr=524
[2020-10-31 18:32:04,408] INFO  <tHubConsumer:24> [dedElastic-3] Message: value="288 - !!0202 yaDveD olleH", partitionId=2, sequenceNr=81
[2020-10-31 18:32:05,314] INFO  <tHubConsumer:24> [dedElastic-5] Message: value="388 - !!0202 yaDveD olleH", partitionId=1, sequenceNr=276
[2020-10-31 18:32:06,355] INFO  <tHubConsumer:24> [dedElastic-3] Message: value="488 - !!0202 yaDveD olleH", partitionId=2, sequenceNr=82
[2020-10-31 18:32:07,303] INFO  <tHubConsumer:24> [dedElastic-6] Message: value="588 - !!0202 yaDveD olleH", partitionId=0, sequenceNr=525
[2020-10-31 18:32:08,411] INFO  <tHubConsumer:24> [dedElastic-3] Message: value="688 - !!0202 yaDveD olleH", partitionId=2, sequenceNr=83
[2020-10-31 18:32:09,359] INFO  <tHubConsumer:24> [dedElastic-6] Message: value="788 - !!0202 yaDveD olleH", partitionId=0, sequenceNr=526
```