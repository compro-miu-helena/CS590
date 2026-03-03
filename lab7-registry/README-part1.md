# Lab 6 - Feign and Registry (Part 1)

This folder contains two services for Part 1:

- `stock-service` (port `8900`)
- `product-service` (port `8901`)

## Service behavior

- `GET http://localhost:8900/stock/{productNumber}` returns a hard-coded stock number.
- `GET http://localhost:8901/product/{productNumber}` returns:
  - `productNumber`
  - `name`
  - `numberOnStock` (fetched from `stock-service` via Feign)

## Run order

1. Start `stock-service`
2. Start `product-service`
3. Call `http://localhost:8901/product/1`

Expected sample response:

```json
{"productNumber":1,"name":"Laptop","numberOnStock":40}
```

## Notes

- Feign dependency is in `product-service/pom.xml`:
  - `org.springframework.cloud:spring-cloud-starter-openfeign`
- Feign client interface:
  - `product-service/src/main/java/lab/productservice/client/StockClient.java`
