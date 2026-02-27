# Spring Integration Lab - Presentation Guide

## 1. Quick Opening (30-45s)

Say this first:

- "This lab demonstrates an ESB-style integration flow with Spring Integration."
- "I implemented four modules: `OrderService`, `EnterpriseServiceBus`, `WarehouseService`, and `ShippingService`."
- "The ESB is the center of routing. Domain services are decoupled and only receive HTTP requests."

## 2. What Problem This Solves (45s)

- We need to process orders in sequence.
- Instead of service-to-service coupling, routing is centralized in ESB channels.
- This makes flow logic easier to control and evolve.

## 3. Architecture You Should Explain (1-2 min)

Use this diagram:

```text
OrderService -> POST /orders -> ESB (8080)
                               |
                               v
                    warehousechannel
                               |
                               v
                    WarehouseActivator -> WarehouseService (8081)
                               |
                               v
                     shippingchannel
                               |
                               v
                     ShippingActivator -> ShippingService (8082)
```

Key message:

- `OrderService` only talks to ESB.
- ESB routes internally using channels + service activators.
- Warehouse and Shipping are independent endpoints.

## 4. Router + ESB Core Implementation (2-3 min)

### 4.1 ESB entry point (`OrderController`)

File path: `C:\Users\mbeua\Área de Trabalho\CS590\springintegration\EnterpriseServiceBus\src\main\java\esb\OrderController.java`

```java
@Autowired
@Qualifier("warehousechannel")
MessageChannel warehouseChannel;

@PostMapping("/orders")
public ResponseEntity<?> receiveOrder(@RequestBody Order order) {
    Message<Order> orderMessage = MessageBuilder.withPayload(order).build();
    warehouseChannel.send(orderMessage);
    return new ResponseEntity<Order>(order, HttpStatus.OK);
}
```

Talking point:

- "The REST controller converts an HTTP payload into a Spring Integration message and sends it to the first router channel."

### 4.2 Channel routing in `esbconfig.xml`

File path: `C:\Users\mbeua\Área de Trabalho\CS590\springintegration\EnterpriseServiceBus\src\main\resources\esbconfig.xml`

```xml
<channel id="warehousechannel"/>
<channel id="shippingchannel"/>

<service-activator input-channel="warehousechannel"
                   output-channel="shippingchannel"
                   ref="warehouseservice"
                   method="checkStock"/>

<service-activator input-channel="shippingchannel"
                   ref="shippingservice"
                   method="ship"/>
```

Talking point:

- "This is the heart of routing. Flow is declarative: warehouse step first, shipping step second."

### 4.3 Activators calling services

File path: `C:\Users\mbeua\Área de Trabalho\CS590\springintegration\EnterpriseServiceBus\src\main\java\esb\WarehouseActivator.java`

```java
public Order checkStock(Order order) {
    System.out.println("WarehouseService: checking order " + order.toString());
    restTemplate.postForLocation("http://localhost:8081/orders", order);
    return order;
}
```

File path: `C:\Users\mbeua\Área de Trabalho\CS590\springintegration\EnterpriseServiceBus\src\main\java\esb\ShippingActivator.java`

```java
public void ship(Order order) {
    System.out.println("shipping: " + order.toString());
    restTemplate.postForLocation("http://localhost:8082/orders", order);
}
```

Talking point:

- "The first activator returns `Order`, so the message continues to the next channel. The second is the last step."

## 5. Monitoring Requirement (item d) (1 min)

Explain that monitoring is currently log-based and demonstrates traceability of each order:

- ESB log:
  - File path: `C:\Users\mbeua\Área de Trabalho\CS590\springintegration\EnterpriseServiceBus\src\main\java\esb\WarehouseActivator.java`
  - File path: `C:\Users\mbeua\Área de Trabalho\CS590\springintegration\EnterpriseServiceBus\src\main\java\esb\ShippingActivator.java`
  - `WarehouseService: checking order ...`
  - `shipping: ...`
- Warehouse log:
  - File path: `C:\Users\mbeua\Área de Trabalho\CS590\springintegration\WarehouseService\src\main\java\esb\WarehouseController.java`
  - `Warehouse Application receiving order: ...`
- Shipping log:
  - File path: `C:\Users\mbeua\Área de Trabalho\CS590\springintegration\ShippingService\src\main\java\esb\ShippingController.java`
  - `Shipping Application receiving order: ...`

Key message:

- "For each order, I can verify it passed through all pipeline stages."

## 6. Live Demo Script (2 min)

Run in this order (4 terminals):

```powershell
cd WarehouseService; .\mvnw.cmd spring-boot:run
cd ShippingService; .\mvnw.cmd spring-boot:run
cd EnterpriseServiceBus; .\mvnw.cmd spring-boot:run
cd OrderService; .\mvnw.cmd spring-boot:run
```

What to show:

1. Ports up: `8081`, `8082`, `8080`.
2. `OrderService` sends two orders (`334`, `355`).
3. Matching logs in all three runtime services.

## 7. Design Decisions (1 min)

- ESB used as central orchestrator.
- Channel-based routers used for clear flow definition.
- Service activators isolate integration logic.
- Synchronous HTTP kept intentionally simple for lab scope.
- No MongoDB/Kafka added because not required by assignment scope.

## 8. Questions You May Get (and short answers)

1. "Why not direct call from OrderService to Warehouse and Shipping?"
- "That would couple orchestration to the producer. ESB keeps flow centralized."

2. "Why no Kafka?"
- "Kafka is a valid improvement, but the lab requirement is satisfied with Spring Integration channels and HTTP."

3. "How would you improve this in production?"
- "Add async broker, retries/circuit breaker, metrics/tracing, and integration tests."

## 9. 30-Second Closing

- "The lab is fully functional end-to-end."
- "Routing is implemented with Spring Integration channels and service activators."
- "Monitoring evidence is shown through correlated logs in ESB, Warehouse, and Shipping."
- "Architecture is clean, decoupled, and ready for incremental improvements."
