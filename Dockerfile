FROM ghcr.io/graalvm/graalvm-ce:java11-21 as graalvm

RUN gu install native-image

COPY . /home/app/micronaut-slf4j-log4j2-native-image
WORKDIR /home/app/micronaut-slf4j-log4j2-native-image

RUN native-image -jar build/libs/micronaut-slf4j-log4j2-0.1-all.jar --trace-class-initialization=org.apache.logging.slf4j.Log4jLogger

#FROM frolvlad/alpine-glibc
#RUN apk update && apk add libstdc++
#EXPOSE 8080
#COPY --from=graalvm /home/app/micronaut-slf4j-log4j2-native-image/micronaut-slf4j-log4j2-native-image /app/micronaut-slf4j-log4j2-native-image
#ENTRYPOINT ["/app/micronaut-slf4j-log4j2-native-image"]
