#!/usr/bin/env bash

set -e -u -
cd spring-boot-service/
./mvnw clean package
cp ./target/*.jar  ../jar-file