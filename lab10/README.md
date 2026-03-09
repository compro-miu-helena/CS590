# Lab 10

This repository contains three Spring Boot microservices secured with Spring Security and Keycloak-compatible JWT validation.

## Project structure

- `serviceA`: product-facing API and entry point for cross-service calls
- `serviceB`: employee contact API
- `serviceC`: salary API

Each service is a separate Maven project with its own `pom.xml` and Maven wrapper.

## Requirements

- Java 17
- Maven, or use the included Maven wrapper scripts
- A Keycloak server running at `http://localhost:8090`
- Realm: `myrealm`
- Client: `myclient`

## Services

### serviceA

- Port: `8081`
- Base path: `/api/a`
- Endpoints:
  - `GET /api/a/product`
    - Roles: `CUSTOMER`, `EMPLOYEE`, `MANAGER`
  - `GET /api/a/employee-contact`
    - Roles: `EMPLOYEE`, `MANAGER`
    - Calls `serviceB`
  - `GET /api/a/salary`
    - Roles: `MANAGER`
    - Calls `serviceC`

### serviceB

- Port: `8082`
- Base path: `/api/b`
- Endpoints:
  - `GET /api/b/employee-contact`

### serviceC

- Port: `8083`
- Base path: `/api/c`
- Endpoints:
  - `GET /api/c/salary`

## Security

All three services:

- require authentication for application endpoints
- expose `/actuator/**` without authentication
- use JWT resource server configuration
- enable method-level security with role checks

## Running the services

Start each service in a separate terminal.

### Windows

```powershell
cd serviceA
.\mvnw.cmd spring-boot:run
```

```powershell
cd serviceB
.\mvnw.cmd spring-boot:run
```

```powershell
cd serviceC
.\mvnw.cmd spring-boot:run
```

### macOS/Linux

```bash
cd serviceA
./mvnw spring-boot:run
```

```bash
cd serviceB
./mvnw spring-boot:run
```

```bash
cd serviceC
./mvnw spring-boot:run
```

## Testing

Run tests per service:

```powershell
cd serviceA
.\mvnw.cmd test
```

```powershell
cd serviceB
.\mvnw.cmd test
```

```powershell
cd serviceC
.\mvnw.cmd test
```

## Notes

- `serviceA` calls `serviceB` at `http://localhost:8082/api/b/employee-contact`
- `serviceA` calls `serviceC` at `http://localhost:8083/api/c/salary`
- The repository does not include Keycloak realm export or container setup, so that must be configured separately
