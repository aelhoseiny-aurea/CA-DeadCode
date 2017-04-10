#!/usr/bin/bash
mkdir docker
cp src/main/docker/dockerFile docker
cp build/libs/ca-deadcode-0.0.1-SNAPSHOT.jar src/main/docker/
docker build -t aurea-training-aelhoseiny/deadcode:latest docker
