# Deploy Azure Kubernetes Service

## Create a resource group

On Azure, a "resource group" is a logical group in which Azure resources are deployed and managed.
A complex deployment could need different resources such as storage, virtual machines, VPN and so on. 
Having them all together in a resource group is really useful for handling them as a whole (for example, deleting the entire deployment with a single command when you're done).

The first step is to create a resource group in a specific Azure location.

```shell
az group create --name strimzigroup --location northeurope
```

## Create a AKS cluster

The `az` tool provides a `aks` command for interacting with AKS in order to create and manage a Kubernetes cluster.
To create a new Kubernetes cluster, the `create` subcommand has different options but the main ones are the destination resource group, the name of the cluster, the related number of nodes and the Kubernetes version to use.

```shell
az aks create --resource-group strimzigroup --name strimzicluster --node-count 3 --generate-ssh-keys --kubernetes-version 1.18.8
```

> It is possible to get the available Kubernetes versions in a specific location running the command `az aks get-versions --location northeurope --output table`

## Connect to the cluster

After downloading and installing the `kubectl` tool, following the official Kubernetes documentation [here](https://kubernetes.io/docs/tasks/tools/install-kubectl/) or using the handy `az aks install-cli`, you need to configure it with the right credentials to connect to the Kubernetes cluster you just deployed in AKS:

```shell
az aks get-credentials --resource-group strimzigroup --name strimzicluster
```

You can verify that the connection with the Kubernetes cluster is working by running the `kubectl get nodes` command to show all the available nodes in the cluster:

```shell
kubectl get nodes

NAME                                STATUS   ROLES   AGE     VERSION
aks-nodepool1-24085136-vmss000000   Ready    agent   2m18s   v1.18.8
aks-nodepool1-24085136-vmss000001   Ready    agent   2m14s   v1.18.8
aks-nodepool1-24085136-vmss000002   Ready    agent   2m21s   v1.18.8
```

## Delete the cluster

When the cluster is no longer needed, use the `az group delete` command to remove the resource group, container service, and all related resources.

```shell
az group delete --name strimzigroup --yes --no-wait
```