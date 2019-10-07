#!/bin/bash
for c in $(docker ps --filter ancestor=isdemidoff-kafka-producer-test -a -q); do
	docker rm -f $c
done

cp ../../../kafka-test-producer/target/*.jar ./
docker build -t isdemidoff-kafka-producer-test ./
rm *.jar
