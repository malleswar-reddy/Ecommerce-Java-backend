#!/bin/bash
# build-all.sh

if [ -z "$1" ] || [ "$1" = "all" ]; then
  echo "Building all Maven projects..."
  mvn clean install -DskipTests
else
  echo "Building $1..."
  mvn clean install -pl "$1" -am -DskipTests
fi
# Check if the build was successful
echo "Building completed."

echo "Docker image building  $1..."
docker-compose -f docker-compose.yml build "$1"
echo "Docker image building completed  $1..."

# Check if the Docker build was successful
#run docker container

docker-compose -f docker-compose.yml up -d "$1"
