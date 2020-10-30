# Deploy Kafka Mirror Maker 2

## Create Secret for Event Hub authentication and deploy Kafka Mirror Maker 2

For authenticating to the Azure Event Hub namespace, the following snippet shows the endpoint that has to be customized with the actual Event Hub connection string; this is provided by the `05-deploy-mm2/00-eventhubs-secret.yaml` file.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: eventhubssecret
type: Opaque
stringData:
  eventhubspassword: Endpoint=sb://<eventhubs-namespace>.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<access-key>
```

The Kafka Mirror Maker 2 instance is deployed via the Strimzi cluster operator through a corresponding `KafkaMirrorMaker2` resource.
It is provided by the `05-deploy-mm2/01-kafka-mirror-maker-2-to-eh.yaml` file and it contains the Event Hub namespace that has to be customized.

```shell
kubectl apply -f 05-deploy-mm2 -n strimzi-demo
```