# Lab4 Part 2 - Product + Shopping + REST Client

Projeto novo e separado do `springmongodemo` com 3 modulos:

- `product-service` (porta `8081`)
- `shopping-service` (porta `8082`)
- `rest-client` (executa passos 1-6 do enunciado)

## Requisitos

- Java 17
- Maven
- Docker Desktop

## MongoDB (2 instancias)

No diretorio `lab4-part2`:

```powershell
docker compose up -d
```

Isso sobe:

- `product-mongo` em `localhost:27017` (db `productdb`)
- `shopping-mongo` em `localhost:27018` (db `shoppingdb`)

## Subir os servicos

Terminal 1:

```powershell
cd product-service
mvn spring-boot:run
```

Terminal 2:

```powershell
cd shopping-service
mvn spring-boot:run
```

## Executar o cliente (passos 1-6)

Terminal 3:

```powershell
cd rest-client
mvn spring-boot:run
```

O cliente imprime no console:

1. add product
2. get product
3. add to shopping cart
4. get shopping cart
5. update product price
6. get shopping cart novamente (com preco atualizado)

## Endpoints

### Product Service

- `POST /products` -> addProduct
- `GET /products/{productNumber}` -> getProduct
- `PUT /products/{productNumber}/price` -> updateProductPrice

### Shopping Service

- `POST /shoppingcart/items` -> addToShoppingCart
- `GET /shoppingcart` -> getShoppingCart

`getShoppingCart` faz sincronizacao de detalhes com `product-service` para refletir mudancas de preco.
