# micronaut-slf4j-log4j2

minimal project to compile micronaut with slf4j and log4j2 using native-image.

## steps to reproduce
1. `sdk use java 21.2.0.r11-grl`  
2. `./gradlew clean build`
3. `native-image -jar build/libs/micronaut-slf4j-log4j2-0.1-all.jar --trace-class-initialization=org.apache.logging.slf4j.Log4jLogger`


error:
```
Error: Classes that should be initialized at run time got initialized during image building:
 org.apache.logging.slf4j.Log4jLogger was unintentionally initialized at build time. io.micronaut.runtime.Micronaut caused initialization of this class with the following trace: 
	at org.apache.logging.slf4j.Log4jLogger.<clinit>(Log4jLogger.java:41)
	at org.apache.logging.slf4j.Log4jLoggerFactory.newLogger(Log4jLoggerFactory.java:39)
	at org.apache.logging.slf4j.Log4jLoggerFactory.newLogger(Log4jLoggerFactory.java:30)
	at org.apache.logging.log4j.spi.AbstractLoggerAdapter.getLogger(AbstractLoggerAdapter.java:53)
	at org.apache.logging.slf4j.Log4jLoggerFactory.getLogger(Log4jLoggerFactory.java:30)
	at org.slf4j.LoggerFactory.getLogger(LoggerFactory.java:363)
	at org.slf4j.LoggerFactory.getLogger(LoggerFactory.java:388)
	at io.micronaut.runtime.Micronaut.<clinit>(Micronaut.java:49)
```