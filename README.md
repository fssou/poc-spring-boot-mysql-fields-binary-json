# Proof of Concept | Spring boot with MySQL database using fields with JSON and Binary type

## Description
This project is a proof of concept to demonstrate how to use fields with JSON and Binary type in a MySQL database using Spring Boot.

## Technologies
- Java 21
- Spring Boot 3.2.2

## How to run
First, you need to have Docker installed on your machine. Then, you can run the following command to start the MySQL database and ELK stack (Elasticsearch, Logstash, Kibana) using Docker Compose:
```bash
docker-compose up -d
```

> _Consider checking this ELK documentation to obtain the enrollment token to use it in the Kibana dashboard: [Documentação](https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html)_

After that, you can run the following command to start the Spring Boot application:
```bash
./mvnw spring-boot:run
```

## How to test
You can use the following command to run the tests:
```bash
./mvnw test
```

## Considerations about the project
- The project is using the `spring-boot-starter-data-jpa` dependency to access the MySQL database.
- The project is using the `spring-boot-starter-web` dependency to expose the REST API.
- The project is using the `spring-boot-starter-validation` dependency to validate the request body.
- The project is using the `spring-boot-starter-test` dependency to test the application.
- The project is using the `spring-boot-starter-logging` dependency to log the application.
- The project is using the `spring-boot-starter-actuator` dependency to monitor the application.

## Future improvements
- Add more tests
  - Add more unit tests
  - Add more integration tests
  - Add more contract tests
  - Add more end-to-end tests
  - Add more performance tests
- Add more logs
- Add more documentation
  - Add more diagrams
  - Add more README
  - Add more Swagger
- Add more comments
- Add more validations
- Add more error handling
  - Exception handling
  - Error handling
  - Validation handling
  - Logging
- Add more security
  - JWT
  - OAuth2
  - HTTPS
  - CORS
  - CSRF
  - SQL Injection
  - XSS
  - Brute force
- Add more performance tests
  - [Gatling](https://github.com/gatling/gatling-funspec-demo)
  - JMeter
  - Locust
  - Apache Bench
  - Tsung
  - Artillery
  - k6
  - wrk
  - Vegeta
  - Siege
  - Loader.io
  - Flood.io
  - Load Impact
  - BlazeMeter
  - LoadRunner
  - WebLOAD
  - NeoLoad
  - LoadNinja
  - SmartMeter.io
- Add more monitoring
  - Prometheus
- Add more CI/CD
  - GitHub Actions
- Add more deployment strategies
  - Blue/Green
  - Canary
- Add more scalability
- Add more reliability
- Add more maintainability
- Add more observability
  - Distributed tracing
  - Log correlation
  - Metrics
  - Alerts
  - Dashboards
- Add more retries
- Add more timeouts
- Add more bulkheads
- Add more cache
- Add more rate limiting
- Add more API Gateway
- Add more message broker
- Add more event sourcing
- Add more CQRS
- Add more DDD
- Add more hexagonal architecture
- Add more microservices
- Add more serverless
- Add more containers
- Add more Kubernetes
- Add more cloud
- Add more chaos engineering
- Add more security tests
  - OWASP ZAP
  - SonarQube
  - Snyk
