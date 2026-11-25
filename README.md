# Fortus
Fortus API backend: an application for managing and tracking workouts.

## Testing the API with Bruno

1. Start the application with `./mvnw spring-boot:run` (the API is available at `http://localhost:8080`).
2. In Bruno, open the existing collection by selecting the `bruno/Fortus API` folder.
3. Select the `Local` environment. It sets `baseUrl` to `http://localhost:8080`; change this variable if the application runs on a different port.

The collection includes the routes mapped by the controllers, with example request bodies for create and edit operations. The default profile uses an in-memory H2 database.
