FROM gradle:7.4.2-jdk17 as gradle
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

ENTRYPOINT ["java","-jar","/home/gradle/src/build/libs/github.jar"]