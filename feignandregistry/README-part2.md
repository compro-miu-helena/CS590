# Lab 6 - Feign and Registry (Part 2)

## Goal
Register `stock-service` and `product-service` in Consul, and let `product-service` call `stock-service` through discovery.

## Dependencies added to both services
- `spring-boot-starter-web`
- `spring-cloud-starter-consul-discovery`
- `spring-boot-starter-actuator`

`product-service` also keeps:
- `spring-cloud-starter-openfeign`

## Consul
Run Consul:

```bash
docker run -d -p 8500:8500 --name=consul hashicorp/consul:latest
```

Open dashboard:
- http://localhost:8500/

## Run order
1. Start `stock-service`
2. Confirm `stock-service` appears in Consul
3. Start `product-service`
4. Confirm `product-service` appears in Consul
5. Call:
   - `http://localhost:8901/product/1`

Expected behavior:
- Request reaches `product-service`
- Feign resolves `stock-service` through Consul by service name
- Response includes stock from `stock-service`

## Key configuration
Both services (`application.properties`):

```properties
spring.application.name=<service-name>
spring.cloud.consul.host=localhost
spring.cloud.consul.port=8500
spring.cloud.consul.discovery.prefer-ip-address=true
```

Feign client in `product-service`:

```java
@FeignClient(name = "stock-service")
```
