FROM maven:3.6.3-openjdk-15 AS builder
WORKDIR /build/
COPY . /build/
RUN mvn -X -e -D maven.test.failure.ignore=true clean package


FROM openjdk:15.0-jdk
WORKDIR /app
COPY --from=builder /build/target/retailStore-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "retailStore-0.0.1-SNAPSHOT.jar"]