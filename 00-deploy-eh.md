# Deploy Azure Event Hubs

## Create an Event Hub namespace

Create an Event Hub namespace where the event hub to mirroring data from Apache Kafka will live.
This event hub has to have the Kafka protocol enabled (see `--enable-kafka true` option).

```shell
az eventhubs namespace create --name devday2020eh --resource-group strimzigroup --location northeurope --enable-kafka true
```

When you want to clean up and delete the Event Hub namespace, just run:

```shell
az eventhubs namespace delete --name devday2020eh --resource-group strimzigroup
```

After creating the Event Hubs namespace, get the shared access key name and value used for connecting to it.
Set the `SHARED_ACCESS_KEY_NAME` and `SHARED_ACCESS_KEY` environment variables with these values and run the following `make` command.

```shell
make patch-eh
```

> the Event Hubs namespace will be `devday2020eh` by default. You can change that setting the `EVENTHUBS_NAMESPACE` environment variable. The `SHARED_ACCESS_KEY_NAME` will be `RootManageSharedAccessKey` by default.