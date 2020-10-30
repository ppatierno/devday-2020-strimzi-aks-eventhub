# Deploy Strimzi

## OperatorHub.io

The [OperatorHub.io](https://operatorhub.io/) website provides a bunch of operators for Kubernetes.

## Install OLM (Operator Lifecycle Manager)

In order to install an operator in the Kubernetes cluster, the OLM (Operator Lifecycle Manager) is needed.
It's a tool to help manage the operators running on your cluster.

```shell
curl -sL https://github.com/operator-framework/operator-lifecycle-manager/releases/download/0.16.1/install.sh | bash -s 0.16.1
```

The OLM component together with the operators catalog will run in the new `olm` namespace.

```shell
kubectl get pods -n olm

NAME                                READY   STATUS    RESTARTS   AGE
catalog-operator-66d58f7877-wjvps   1/1     Running   0          97s
olm-operator-5f75dd4c6c-b4mc6       1/1     Running   0          97s
operatorhubio-catalog-wd2ww         1/1     Running   0          86s
packageserver-6bc86b98f9-4kn4w      1/1     Running   0          71s
packageserver-6bc86b98f9-nqn5t      1/1     Running   0          84s
```

## Install the Strimzi operator

Install the operator by running the following command.

```shell
kubectl create -f https://operatorhub.io/install/strimzi-kafka-operator.yaml
```

Follow the installation process of the latest Strimzi operator version (0.20.0).

```shell
kubectl get csv -n operators -w

NAME                               DISPLAY   VERSION   REPLACES                           PHASE
strimzi-cluster-operator.v0.20.0   Strimzi   0.20.0    strimzi-cluster-operator.v0.19.0   Installing
strimzi-cluster-operator.v0.20.0   Strimzi   0.20.0    strimzi-cluster-operator.v0.19.0   Succeeded
```

Finally, the related pod is running in the `operators` namespace.

```shell
kubectl get pods -n operators

NAME                                                READY   STATUS    RESTARTS   AGE
strimzi-cluster-operator-v0.20.0-779785689c-pq9fm   1/1     Running   0          106s
```

The operator brings all the CRDs (Custom Resource Definitions) which it's able to handle like `Kafka`, `KafkaConnect`, `KafkaMirrorMaker`, `KafkaMirrorMaker2`, `KafkaBridge`, `KafkaTopic`, `KafkaUser` and `KafkaRebalance`. 
It will watch for the related custom resource creation in all the namespaces on the Kubernetes cluster.