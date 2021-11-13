FROM gradle:7.2.0-jdk11 as builder
WORKDIR /usr/src/app
COPY --chown=gradle:gradle . /usr/src/app
WORKDIR /usr/src/app
RUN gradle bootJar

FROM openjdk:11.0.12-jre-slim-buster
EXPOSE 5000
COPY --from=builder /usr/src/app/build/libs/hackathon21-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/hackathon21-0.0.1-SNAPSHOT.jar"]