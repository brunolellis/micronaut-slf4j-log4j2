#!/bin/sh
docker build . -t micronaut-slf4j-log4j2-native-image
echo "docker run -p 8080:8080 micronaut-slf4j-log4j2-native-image"