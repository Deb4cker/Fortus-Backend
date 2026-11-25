# Fortus
Fortus API backend: an application for managing and tracking workouts.

## Testing the API with Bruno

1. Start the application with `./mvnw spring-boot:run` (the API is available at `http://localhost:8080`).
2. In Bruno, open the existing collection by selecting the `bruno/Fortus API` folder.
3. Select the `Local` environment. It sets `baseUrl` to `http://localhost:8080`; change this variable if the application runs on a different port.

The collection includes the routes mapped by the controllers, with example request bodies for create and edit operations. The default profile uses an in-memory H2 database.

## Running with Docker Compose

Start the API and PostgreSQL with:

```sh
docker compose up --build
```

The API is available at `http://localhost:8080`, and PostgreSQL is exposed on port `5432`. The Compose setup uses the `dev` profile, waits for PostgreSQL to become healthy, and stores database files in the `postgres_data` volume. The default local database credentials are `fortus` / `fortus`; set `POSTGRES_DB`, `POSTGRES_USER`, and `POSTGRES_PASSWORD` in your environment to change them. You can also change the host ports with `APP_PORT` and `POSTGRES_PORT`.

Stop the services with `docker compose down`. The database volume is retained; `docker compose down -v` also removes it and deletes the stored database data.
