# Pricing Service



## Arquitectura

El proyecto sigue una arquitectura hexagonal.

La idea principal es separar claramente:
- La lógica de negocio (dominio)
- Los casos de uso
- Los detalles técnicos (REST, JPA, métricas, etc.)

Esta separación está validada mediante tests de arquitectura con ArchUnit.

---

## Estructura de paquetes

com.inditex.pricing
- domain
    - model: modelo de dominio
    - port.in: casos de uso
    - port.out: contratos de persistencia
    - service: implementación de la lógica de negocio

- application
    - dto: objetos de intercambio con la API

- infrastructure
    - inbound.rest: controladores REST
    - outbound.jpa: persistencia JPA
    - observability: métricas y monitorización

---

## Dominio

El dominio contiene la lógica principal del negocio:
- Selección del precio aplicable en función de la fecha
- Resolución de conflictos por prioridad

No depende de Spring, JPA ni de ninguna librería externa.

---

## Persistencia

Se utiliza una base de datos H2 en memoria.
Los datos de ejemplo se cargan mediante Flyway.

La persistencia se implementa como un adaptador JPA que cumple un puerto definido en el dominio,
evitando que el dominio conozca detalles de base de datos.

---

## API REST

Endpoint principal:

GET /api/prices

Parámetros:
- brandId
- productId
- applicationDate (ISO-8601)

Ejemplo de respuesta:

{
"productId": 35455,
"brandId": 1,
"priceList": 2,
"startDate": "2020-06-14T15:00:00",
"endDate": "2020-06-14T18:30:00",
"price": 25.45,
"currency": "EUR"
}

El controlador REST no contiene lógica de negocio, solo actúa como adaptador
y delega en el caso de uso del dominio.

---

## Testing

Se han implementado distintos niveles de test:

- Tests unitarios del dominio (sin Spring)
- Tests de integración del repositorio JPA con H2
- Tests de sistema del endpoint REST, cubriendo los 5 escenarios del enunciado
- Tests de arquitectura con ArchUnit para garantizar la separación de capas

---

## Observabilidad

El servicio expone métricas mediante Spring Boot Actuator y Prometheus.

Endpoints disponibles:
- /actuator/health
- /actuator/prometheus

Se incluyen métricas estándar (JVM, HTTP, base de datos) y una métrica custom
para contar el número de consultas de precios.

---

## Ejecución

Arrancar la aplicación:
mvn spring-boot:run

Ejecutar los tests:
mvn test

---

## Documentación API

Swagger UI disponible en:
http://localhost:8080/swagger-ui.html

---

