# Deploy internal applications

## Producer and Consumer

Deploy a couple of applications communicating through an internal topic.

```shell
kubectl apply -f 05-deploy-internal-applications -n strimzi-demo
```

From the corresponding logs you can check how the producer, and the consumer are exchanging messages.

```shell
kubectl logs devday2020-producer-d5b5bd64f-b7cwg -n strimzi-demo -f

...
2020-10-31 17:17:54 INFO  KafkaProducerExample:69 - Sending messages "Hello DevDay 2020!! - 33"
2020-10-31 17:17:55 INFO  KafkaProducerExample:69 - Sending messages "Hello DevDay 2020!! - 34"
2020-10-31 17:17:56 INFO  KafkaProducerExample:69 - Sending messages "Hello DevDay 2020!! - 35"
...
```

```shell
kubectl logs devday2020-consumer-bd75b6687-k2rss -n strimzi-demo -f

2020-10-31 17:17:54 INFO  KafkaConsumerExample:47 - Received message:
2020-10-31 17:17:54 INFO  KafkaConsumerExample:48 -     partition: 1
2020-10-31 17:17:54 INFO  KafkaConsumerExample:49 -     offset: 10
2020-10-31 17:17:54 INFO  KafkaConsumerExample:50 -     value: "Hello DevDay 2020!! - 33"
2020-10-31 17:17:54 INFO  KafkaConsumerExample:52 -     headers: 
2020-10-31 17:17:55 INFO  KafkaConsumerExample:47 - Received message:
2020-10-31 17:17:55 INFO  KafkaConsumerExample:48 -     partition: 0
2020-10-31 17:17:55 INFO  KafkaConsumerExample:49 -     offset: 15
2020-10-31 17:17:55 INFO  KafkaConsumerExample:50 -     value: "Hello DevDay 2020!! - 34"
2020-10-31 17:17:55 INFO  KafkaConsumerExample:52 -     headers: 
2020-10-31 17:17:56 INFO  KafkaConsumerExample:47 - Received message:
2020-10-31 17:17:56 INFO  KafkaConsumerExample:48 -     partition: 1
2020-10-31 17:17:56 INFO  KafkaConsumerExample:49 -     offset: 11
2020-10-31 17:17:56 INFO  KafkaConsumerExample:50 -     value: "Hello DevDay 2020!! - 35"
2020-10-31 17:17:56 INFO  KafkaConsumerExample:52 -     headers: 
```