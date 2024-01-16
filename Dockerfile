# SETUP BUILDER
# LOCAL
FROM maven:3.8-openjdk-17 AS builder
# SERVER
# FROM jtl-tkgiharbor.hq.bni.co.id/soadev/maven-3-8-4-openjdk-17-slim AS builder

# SETUP PROXY (NO NEDD ON LOCAL)
# ENV http_proxy 192.168.45.105:8080
# ENV https_proxy 192.168.45.105:8080
# ENV DEBIAN_FRONTEND noninteractive

# COPY FILES
RUN mkdir -p /app/qris-network-service
COPY ./ /app/qris-network-service/

# BUILD
WORKDIR /app/qris-network-service
# LOCAL
RUN mvn clean package -Dmaven.test.skip=true
# SERVER
# RUN mvn -Dhttp.proxyHost=192.168.45.105 -Dhttp.proxyPort=8080 -Dhttps.proxyHost=192.168.45.105 -Dhttps.proxyPort=8080 clean package -Dmaven.test.skip=true

# LOCAL
FROM openjdk:17-slim
# SERVER
# FROM jtl-tkgiharbor.hq.bni.co.id/soadev/openjdk-17-slim

# SETUP PROXY (NO NEED ON LOCAL)
# ENV http_proxy 192.168.45.105:8080
# ENV https_proxy 192.168.45.105:8080
# ENV DEBIAN_FRONTEND noninteractive

# CREATE DIR AND COPY FILES
COPY --from=builder /app/qris-network-service/target/qris-network-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

# RUN APP
ENTRYPOINT ["java","-jar","qris-network-service-0.0.1-SNAPSHOT.jar"]