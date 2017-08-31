FROM java:8

WORKDIR ./Account

ENV PORT 4567

ADD build.gradle /Account/build.gradle
ADD gradlew /Account/gradlew
ADD gradle /Account/gradle
ADD src /Account/src

RUN ["./gradlew", "clean"]
RUN ["./gradlew", "fatJar"]

EXPOSE 4567
ENTRYPOINT ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "build/libs/Account-1.0-SNAPSHOT.jar"]