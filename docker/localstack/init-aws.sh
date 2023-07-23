#!/bin/bash
awslocal sqs create-queue --queue-name money-transfer-dlq
awslocal sqs create-queue --queue-name money-transfer --attributes file:///etc/localstack/init/ready.d/money-transfer.json