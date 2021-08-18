import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "1.5.4"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32"
}

version = "0.1"
group = "com.github.bruno"

val kotlinVersion = project.properties["kotlinVersion"]
val awsSdkVersion = "2.16.47"
val jacksonVersion = "2.12.3"

repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.github.bruno.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut.aws:micronaut-aws-sdk-v2")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-prometheus")
    implementation("javax.annotation:javax.annotation-api")
    compileOnly("org.graalvm.nativeimage:svm")

    implementation("org.cache2k:cache2k-core:2.2.1.Final")
    implementation("org.cache2k:cache2k-addon:2.2.1.Final")
    implementation("com.newrelic.agent.java:newrelic-api:7.1.1")

    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")

    implementation("software.amazon.awssdk:dynamodb:${awsSdkVersion}")
    implementation("software.amazon.awssdk:sqs:${awsSdkVersion}")
    implementation("software.amazon.awssdk:sts:${awsSdkVersion}")

    implementation("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jacksonVersion}")

    implementation("org.apache.logging.log4j:log4j-core:2.14.1")
    runtimeOnly("org.apache.logging.log4j:log4j-api:2.14.1")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
//    implementation("org.slf4j:jcl-over-slf4j:1.7.32")
    compile("com.lmax:disruptor:3.4.2")

}

application {
    mainClass.set("com.github.bruno.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    shadowJar {
        transform(Log4j2PluginsCacheFileTransformer())
        mergeServiceFiles()
    }

//    nativeImage {
//        args(
//            "-H:ReflectionConfigurationFiles=../resources/main/META-INF/native-image/reflect-config.json",
//            "--trace-class-initialization=org.apache.logging.slf4j.Log4jLogger",
//            "--initialize-at-build-time=org.apache.logging.slf4j.Log4jLogger"
//        )
//        setImageName("micronaut-slf4j-log4j2")
//    }
}

allprojects {
    configurations {
        all {
            exclude(group = "commons-logging") // fix apache logging
        }
    }
}