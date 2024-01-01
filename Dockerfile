FROM maven:3.8-openjdk-17 AS builder

RUN mkdir -p /app/qris-network-service

WORKDIR /app

COPY ./ /app/qris-network-service/

WORKDIR /app/qris-network-service

RUN mvn  clean package -Dmaven.test.skip=true

FROM openjdk:17-slim

COPY --from=builder /app/qris-network-service/target/qris-network-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar","qris-network-service-0.0.1-SNAPSHOT.jar"]