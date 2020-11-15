export EVENTHUBS_NAMESPACE ?= devday2020eh
export ACCESS_KEY_NAME ?= RootManageSharedAccessKey

ifndef ACCESS_KEY
$(error ACCESS_KEY is not set)
endif

#.PHONY: patch
patch:
	sed -i 's/<eventhubs-namespace>/$(EVENTHUBS_NAMESPACE)/' ./azure-eh-consumer/azure-eh-consumer.sh
	sed -i 's/<access-key-name>/$(ACCESS_KEY_NAME)/' ./azure-eh-consumer/azure-eh-consumer.sh
	sed -i 's/<access-key>/$(ACCESS_KEY)/' ./azure-eh-consumer/azure-eh-consumer.sh
	sed -i 's/<eventhubs-namespace>/$(EVENTHUBS_NAMESPACE)/' ./06-deploy-mm2/00-eventhubs-secret.yaml
	sed -i 's/<eventhubs-namespace>/$(EVENTHUBS_NAMESPACE)/' ./06-deploy-mm2/01-kafka-mirror-maker-2-to-eh.yaml
	sed -i 's/<access-key-name>/$(ACCESS_KEY_NAME)/' ./06-deploy-mm2/00-eventhubs-secret.yaml
	sed -i 's/<access-key>/$(ACCESS_KEY)/' ./06-deploy-mm2/00-eventhubs-secret.yaml