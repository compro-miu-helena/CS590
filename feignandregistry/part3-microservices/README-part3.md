# Lab 6 - Feign and Registry (Part 3)

This is a microservice split of the Lab 4 solution into:

- `product-service` (port `8081`)
- `shopping-service` (port `8082`)
- `webshop-client` (command-line runner)

All components use Consul service discovery.

## Architecture

- `shopping-service` calls `product-service` with Feign (`@FeignClient(name="product-service")`) when adding products to carts.
- `product-service` calls `shopping-service` with Feign (`@FeignClient(name="shopping-service")`) when an existing product is updated, so matching products in carts are refreshed.
- `webshop-client` calls both services via Feign service names through Consul.

## Run order

1. Start MongoDB on `localhost:27017`
2. Start Consul:

```bash
docker run -d -p 8500:8500 --name=consul hashicorp/consul:latest
```

3. Start `product-service`
4. Start `shopping-service`
5. Start `webshop-client`

## What should happen

The client does:

1. Create products `A33` and `A34`
2. Add them to cart `1`
3. Print cart
4. Update product `A33` price from `450.0` to `550.0`
5. Print cart again

Expected result: cart line for product `A33` reflects the updated price after step 4.

## Key endpoints

- Product service:
  - `POST /products`
  - `GET /products/{productnumber}`
- Shopping service:
  - `POST /cart/{cartId}`
  - `GET /cart/{cartId}`
  - `POST /internal/product-change` (used by product service for propagation)
