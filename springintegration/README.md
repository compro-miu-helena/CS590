# Spring Integration Lab

## 1. Overview

This lab implements an integration architecture based on an **ESB (Enterprise Service Bus)** using Spring Boot and Spring Integration.

The goal is to process `Order` messages in stages:

1. Receive the order in the ESB.
2. Route it to `WarehouseService` for stock check.
3. Route it to `ShippingService` for shipping processing.

Project modules:

- `EnterpriseServiceBus` (port `8080`)
- `WarehouseService` (port `8081`)
- `ShippingService` (port `8082`)
- `OrderService` (order producer client)

## 2. Architecture (Including Simplified Diagram)

### Architectural style: ESB

`EnterpriseServiceBus` centralizes routing and flow orchestration.  
Domain services (`WarehouseService`, `ShippingService`) are decoupled and do not call each other directly.

### Simplified diagram

```text
OrderService
    |
    | HTTP POST /orders
    v
EnterpriseServiceBus (8080)
  OrderController -> warehousechannel -> WarehouseActivator
                                   |
                                   | HTTP POST /orders
                                   v
                           WarehouseService (8081)
                                   |
                                   | returns Order payload to SI flow
                                   v
                 shippingchannel -> ShippingActivator
                                   |
                                   | HTTP POST /orders
                                   v
                           ShippingService (8082)
```

## 3. Implemented Functional Requirements

- Order intake through ESB REST endpoint (`POST /orders`).
- Internal routing with Spring Integration channels:
  - `warehousechannel`
  - `shippingchannel`
- Chained `service-activator` processing:
  - `WarehouseActivator.checkStock(Order)`
  - `ShippingActivator.ship(Order)`
- HTTP integration with external services:
  - ESB -> WarehouseService
  - ESB -> ShippingService
- Test order generation in `OrderService` (`334` and `355`).
- Log-based monitoring (requirement item d).

## 4. Technologies Used

- Java 17
- Spring Boot 3.5.6
- Spring Integration (XML configuration)
- Spring Web (`RestController`, `RestTemplate`)
- Maven Wrapper (`mvnw`, `mvnw.cmd`)

## 5. How to Run

### Prerequisites

- Java 17 enabled (`java -version`)
- Internet access on first build to download dependencies

### Build

Run inside each module:

```powershell
.\mvnw.cmd clean -DskipTests package
```

### Start services (required order)

Open 4 terminals in `springintegration`:

```powershell
# Terminal 1
cd WarehouseService
.\mvnw.cmd spring-boot:run

# Terminal 2
cd ShippingService
.\mvnw.cmd spring-boot:run

# Terminal 3
cd EnterpriseServiceBus
.\mvnw.cmd spring-boot:run

# Terminal 4
cd OrderService
.\mvnw.cmd spring-boot:run
```

## 6. Processing Flow

1. `OrderService` sends two `POST` requests to `http://localhost:8080/orders`.
2. ESB `OrderController` wraps payload into `Message<Order>` and sends it to `warehousechannel`.
3. `WarehouseActivator`:
   - logs processing step;
   - sends the order to `WarehouseService` (`8081`);
   - returns the order to continue the integration flow.
4. Returned payload moves to `shippingchannel`.
5. `ShippingActivator`:
   - logs processing step;
   - sends the order to `ShippingService` (`8082`).
6. Flow completes.

Real expected output sample:

```text
Warehouse Application receiving order: Order{orderNumber='334', amount=120.0}
Shipping Application receiving order: Order{orderNumber='334', amount=120.0}
WarehouseService: checking order order: nr=334 amount=120.0
shipping: order: nr=334 amount=120.0
```

The same lines appear for order `355`.

## 7. Architectural Decisions

- **ESB as central integration point**: simplifies routing governance and flow evolution.
- **Channel-based routers (`warehousechannel`, `shippingchannel`)**: routing is explicit and declarative in `esbconfig.xml`.
- **Service activators**: isolate integration logic with each external service.
- **Simple service separation**: Warehouse and Shipping can evolve independently.
- **Dedicated producer (`OrderService`)**: validates end-to-end behavior without external clients.

## 8. Possible Future Improvements

- Replace `DirectChannel` with async messaging (JMS/Kafka/RabbitMQ).
- Add retries, timeout policies, and circuit breaker for HTTP calls.
- Add correlation IDs and distributed tracing.
- Expose metrics with Micrometer/Prometheus.
- Replace `RestTemplate` with `WebClient`.
- Add automated end-to-end integration tests.

## Monitoring (Explicit Requirement: item d)

Current monitoring is implemented as **log-observable flow tracking**, in three layers:

1. **ESB routing layer**:
   - `WarehouseActivator` and `ShippingActivator` logs show each pipeline step.
2. **Service processing confirmation**:
   - `WarehouseService` and `ShippingService` log incoming orders.
3. **Spring Integration internal monitoring**:
   - framework startup logs show creation/registration of `errorChannel`.

Real monitoring log examples:

```text
WarehouseService: checking order order: nr=334 amount=120.0
shipping: order: nr=334 amount=120.0
Warehouse Application receiving order: Order{orderNumber='334', amount=120.0}
Shipping Application receiving order: Order{orderNumber='334', amount=120.0}
```

This allows verification that each order passed through all configured routers/channels and reached the final destination.
