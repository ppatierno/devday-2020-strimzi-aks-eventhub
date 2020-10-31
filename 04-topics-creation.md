# Topics creation

## KafkaTopic resources

In order to create a topic through the Topic Operator, the provided `KafkaTopic` custom resources can be deployed.

```shell
kubectl apply -f 04-topics-creation -n strimzi-demo
```

A Kafka topic is now available as a Kubernetes native resource.

```shell
kubectl get kafkatopic -n strimzi-demo

NAME               CLUSTER      PARTITIONS   REPLICATION FACTOR
devday2020-topic   my-cluster   3            3
internal-topic     my-cluster   3            3
```

You can check the topics were created by the Topic Operator in the Kafka cluster by running.

```shell
kubectl exec -it my-cluster-kafka-0 -c kafka -n strimzi-demo -- bin/kafka-topics.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --list

devday2020-topic
internal-topic
```

If you want to get more information like the number of partitions, the leaders and so on.

```shell
kubectl exec -it my-cluster-kafka-0 -c kafka -n strimzi-demo -- bin/kafka-topics.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --describe

Topic: devday2020-topic PartitionCount: 3       ReplicationFactor: 3    Configs: segment.bytes=1073741824,retention.ms=7200000,message.format.version=2.6-IV0
        Topic: devday2020-topic Partition: 0    Leader: 1       Replicas: 1,0,2 Isr: 1,0,2
        Topic: devday2020-topic Partition: 1    Leader: 0       Replicas: 0,2,1 Isr: 0,2,1
        Topic: devday2020-topic Partition: 2    Leader: 2       Replicas: 2,1,0 Isr: 2,1,0
Topic: internal-topic   PartitionCount: 3       ReplicationFactor: 3    Configs: segment.bytes=1073741824,retention.ms=7200000,message.format.version=2.6-IV0
        Topic: internal-topic   Partition: 0    Leader: 1       Replicas: 1,2,0 Isr: 1,2,0
        Topic: internal-topic   Partition: 1    Leader: 0       Replicas: 0,1,2 Isr: 0,1,2
        Topic: internal-topic   Partition: 2    Leader: 2       Replicas: 2,0,1 Isr: 2,0,1
```