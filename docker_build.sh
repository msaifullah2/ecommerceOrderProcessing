#!/bin/sh

echo "Building Project"
mvn clean install

COMMIT_ID=$(git rev-parse --short HEAD)
echo "Building Docker image with tag: ${COMMIT_ID}"
docker build -t order-microservice:$COMMIT_ID .