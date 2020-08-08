#!/bin/bash

echo "Performing clean maven build"
./mvnw clean package -DskipTests=true

echo "Building spring-manager service"
docker build --tag spring-medicine .
