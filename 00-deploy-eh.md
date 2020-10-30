# Deploy Event Hubs

## Create an Event Hub namespace

Create an Event Hub namespace where the event hub to mirroring data from Apache Kafka will live.
This event hub has to have the Kafka protocol enabled (see `--enable-kafka true` option).

```shell
az eventhubs namespace create --name devday2020eh --resource-group strimzigroup --location northeurope --enable-kafka true
```