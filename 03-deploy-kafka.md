# Deploy Apache Kafka

## Kafka resource

An Apache Kafka cluster is described by a `Kafka` custom resource with the declaration of Kafka brokers (replicas, configuration, listeners, storage, ...), ZooKeeper nodes, users and topics operators.

To deploy a Kafka cluster using the provided `Kafka` resource, just run:

```shell
kubectl apply -f 03-deploy-kafka -n strimzi-demo
```

The Cluster Operator takes care of this resource and starts to deploy all the stuff needed for having the Kafka cluster up and running.
At the end of the process you can check the deployed pods (you can also follow the process in real time using the `-w` option).

```shell
kubectl get pods -n strimzi-demo

NAME                                          READY   STATUS    RESTARTS   AGE
my-cluster-entity-operator-65f988dcc4-2f2qm   3/3     Running   0          35s
my-cluster-kafka-0                            1/1     Running   0          64s
my-cluster-kafka-1                            1/1     Running   0          64s
my-cluster-kafka-2                            1/1     Running   0          64s
my-cluster-zookeeper-0                        1/1     Running   0          86s
my-cluster-zookeeper-1                        1/1     Running   0          86s
my-cluster-zookeeper-2                        1/1     Running   0          86s
```

It is possible to check the PVCs (Persistent Volume Claims) created for the related persistent storage.

```shell
kubectl get pvc -n strimzi-demo

NAME                          STATUS   VOLUME                                     CAPACITY   ACCESS MODES   STORAGECLASS   AGE
data-0-my-cluster-kafka-0     Bound    pvc-361d0eb8-0885-44ef-971e-41c2fadea007   100Gi      RWO            default        7m55s
data-0-my-cluster-kafka-1     Bound    pvc-715d9e67-f595-4acb-89df-546636d4a342   100Gi      RWO            default        7m55s
data-0-my-cluster-kafka-2     Bound    pvc-665b0b49-65e4-4d74-9d98-8f3f5f8bb49b   100Gi      RWO            default        7m55s
data-my-cluster-zookeeper-0   Bound    pvc-4841db24-b9ee-40b1-8dcb-ca58bcd2043f   100Gi      RWO            default        9m38s
data-my-cluster-zookeeper-1   Bound    pvc-806721ee-9a31-4de1-9d93-186fef515ddd   100Gi      RWO            default        9m38s
data-my-cluster-zookeeper-2   Bound    pvc-6e6a7bac-9eec-4f8d-86e1-a5402c7952a0   100Gi      RWO            default        9m38s
```

They are bound to Azure Disks that are deployed for you in the same resource group.

> For check the Azure Disks use the `az disk list` command.

Finally, in order to access the Kafka brokers, from inside the Kubernetes cluster, the following "bootstrap" service is created by the operator.
This "bootstrap" service is used for the first connection from clients.

```shell
kubectl get service -n strimzi-demo

NAME                                  TYPE           CLUSTER-IP     EXTERNAL-IP     PORT(S)                      AGE
my-cluster-kafka-bootstrap            ClusterIP      10.0.10.67     <none>          9091/TCP,9092/TCP,9093/TCP   7m55s
my-cluster-kafka-brokers              ClusterIP      None           <none>          9091/TCP,9092/TCP,9093/TCP   7m55s
my-cluster-zookeeper-client           ClusterIP      10.0.122.186   <none>          2181/TCP                     9m52s
my-cluster-zookeeper-nodes            ClusterIP      None           <none>          2181/TCP,2888/TCP,3888/TCP   9m52s
```

The Kafka cluster is now available as a Kubernetes native resource.

```shell
kubectl get kafka -n strimzi-demo

NAME         DESIRED KAFKA REPLICAS   DESIRED ZK REPLICAS
my-cluster   3                        3
```

Finally, to delete the cluster, just delete the corresponding custom resource.

```shell
kubectl delete kafka my-cluster -n strimzi-demo
```