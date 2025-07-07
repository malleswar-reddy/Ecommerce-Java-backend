# 🔩 Microservices Configuration & Startup Guide

---

## 📁 Index

| # | Section                                                     |
|---|-------------------------------------------------------------|
| 1 | [Service Endpoints](#1-service-endpoints)                   |
| 2 | [Service Health Check](#2-service-health-check)             |
| 3 | [Required Dependencies](#3-required-dependencies)           |
| 4 | [Build the Project](#4-build-the-project)                   |
| 5 | [Run the Services](#5-run-the-services)                     |
| 6 | [Example Config Server URLs](#6-example-config-server-urls) |
| 7 | [Notes](#7-notes)                                           |

---

## 1. ✅ Service Endpoints

| **Service**             | **Config Server Fetch URL Example**                                                                    |
|-------------------------|--------------------------------------------------------------------------------------------------------|
| Eureka Dashboard        | [http://localhost:8761](http://localhost:8761)                                                         |
| Admin Service           | [http://localhost:8888/adminservice/default](http://localhost:8888/adminservice/default)               |
| User Service            | [http://localhost:8888/userservice/default](http://localhost:8888/userservice/default)                 |
| Product Service         | [http://localhost:8888/productservice/default](http://localhost:8888/productservice/default)           |
| Order Service           | [http://localhost:8888/orderservice/default](http://localhost:8888/orderservice/default)               |
| Payment Service         | [http://localhost:8888/paymentservice/default](http://localhost:8888/paymentservice/default)           |
| Notification Service    | [http://localhost:8888/notificationservice/default](http://localhost:8888/notificationservice/default) |
| Gateway Service         | [http://localhost:8888/gatewayservice/default](http://localhost:8888/gatewayservice/default)           |
| Admin Service (dev)     | [http://localhost:8888/adminservice/dev](http://localhost:8888/adminservice/dev)                       |
| Discovery Service (dev) | [http://localhost:8888/discoveryservice/dev](http://localhost:8888/discoveryservice/dev)               |

---

## 2. ❤️‍🩹 Service Health Check

Access the actuator health endpoint of any service:

```
http://localhost:<SERVICE_PORT>/actuator/health
```

Example for Payment Service running on port **8080**:

```
http://localhost:8080/actuator/health
```

---

## 3. 📦 Required Dependencies

In the `pom.xml` file of **each microservice**, add the following dependencies to enable **Eureka Client** registration
and **Config Server** fetching:

```xml

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

---

## 4. 🔧 Build the Project

Build the project using Maven:

```bash
# Clean and build the entire project
mvn clean install

# Clean build and skip tests
mvn clean install -DskipTests

# Create JAR files without running tests
mvn clean package -DskipTests
```

---

## 5. 🚀 Run the Services

You can run all the required services using Docker Compose:

```bash
docker-compose build && docker-compose up -d \
paymentservice discoveryservice configserver adminservice \
userservice productservice orderservice notificationservice gatewayservice
```

🔚 This will start the following services:

* **discoveryservice** - Eureka server
* **configserver** - Externalized service configuration
* **adminservice**
* **userservice**
* **productservice**
* **orderservice**
* **paymentservice**
* **notificationservice**
* **gatewayservice**

---

## 6. 🔗 Example Config Server URLs

You can verify your configuration using these example URLs:

| Service Name   | Profile | Example URL                                                                              |
|----------------|---------|------------------------------------------------------------------------------------------|
| adminservice   | default | [http://localhost:8888/adminservice/default](http://localhost:8888/adminservice/default) |
| userservice    | default | [http://localhost:8888/userservice/default](http://localhost:8888/userservice/default)   |
| paymentservice | dev     | [http://localhost:8888/paymentservice/dev](http://localhost:8888/paymentservice/dev)     |

---

## 7. 📝 Notes

* Make sure your **Config Server** is running before starting other services.
* Each service’s `bootstrap.yml` or `application.yml` should contain:

```yaml
spring:
  application:
    name: <service-name>
  config:
    import: "configserver:"
```

Example for PaymentService:

```yaml
spring:
  application:
    name: paymentservice
  config:
    import: "configserver:"
```

* Ensure **Eureka Server** is up at `http://localhost:8761` before other services register.
* Keep your remote Git config repository (for the config server) publicly accessible or configure Git authentication
  properly.

---
