# spring-boot-api
Multi module template for Spring Boot 2.0 with Spring Cloud Zuul

### Build and run application

```bash
./mvnw install && ./mvnw spring-boot:run -pl api-rest
```
Resource swagger UI: http://localhost:8080/api/swagger-ui.html#/message45controller

![alt text](http://drive.google.com/uc?export=view&id=1iFhJRZiG1xS49fUyIXlcGE1SBQX3XNms)

### Send message request via API-Gateway
```bash
curl http://localhost:8090/api/messages?token=123
```

### Reference
- https://spring.io/guides/gs/multi-module/


