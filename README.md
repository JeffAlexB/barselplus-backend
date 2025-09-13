# BarselPlus – Backend

Spring Boot + PostgreSQL backend for the BarselPlus healthcare application.
Provides a REST API for patient, partner, pregnancy, and medical history data.

---

## Quickstart
Prerequisites:
 - Java 21+
 - Maven 3.9+
 - PostgreSQL 16 (local or Docker)

Running locally
````
# Clone the repo
git clone https://github.com/JeffAlexB/BarselPlus-Backend.git
cd BarselPlus-Backend

# Configure environment variables
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/barselplus
export SPRING_DATASOURCE_USERNAME=barselplus
export SPRING_DATASOURCE_PASSWORD=barselplus

# Run the app
./mvnw spring-boot:run
````
Backend spins up at:
`http://localhost:8080/api`

---

## API
OpenAPI docs: http://localhost:8080/swagger-ui.html </br>
JSON docs: http://localhost:8080/v3/api-docs </br>

Endpoint examples:
 - GET `/api/patients/{id}`
 - POST `/api/patients`
 - GET `/api/pregnancies/{id}`

--- 

## Testing
Run unit + integration tests:
````
./mvnw test
````
Integration tests use MockMvc for REST controllers and validate:
 - Entity creation & retrieval
 - Validation errors
 - Role-based access restrictions

---

## Project Structure
````
src/
 ├─ main/
 │   ├─ java/com/barselplus/
 │   │   ├─ controller/      # REST + Web controllers
 │   │   ├─ service/         # Business logic
 │   │   ├─ repository/      # JPA repositories
 │   │   ├─ domain/          # Entities
 │   │   ├─ dto/             # Data Transfer Objects
 │   │   └─ mapper/          # DTO <-> Entity mappers
 │   └─ resources/
 │       └─ application.yml  # Config
 └─ test/
     └─ java/com/barselplus/ # Unit + integration tests

````

## Related Repos
<a href="https://github.com/JeffAlexB/BarselPlus/">BarselPlus (Umbrella Docs)</a> </br>
<a href="https://github.com/JeffAlexB/barselplus-frontend">BarselPlus-Frontend (WIP)</a>

---

## Roadmap
- [ ] Expand entity coverage for larger vertical slice (lab results, fundus measurements, visits)
- [ ] Add Docker Compose for backend + database
- [ ] Integration with Angular frontend
- [ ] CI/CD pipeline with GitHub Actions?
- [ ] Extended testing, including DB validation

--- 

## License
MIT License – <a href="https://github.com/JeffAlexB/barselplus-backend/blob/main/LICENSE">see LICENSE</a>
