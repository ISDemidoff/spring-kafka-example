#!/bin/bash
for c in $(docker ps --filter ancestor=isdemidoff-kafka-consumer-test -a -q); do
	docker rm -f $c
done

cp ../../../kafka-test-consumer/target/*.jar ./
docker build -t isdemidoff-kafka-consumer-test ./
rm *.jar
