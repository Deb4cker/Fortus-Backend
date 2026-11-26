package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.service.response.ApiErrorResponse;
import com.pmnato.fortus.service.response.ApiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobalExceptionHandlerTest {

    @Test
    void validationErrorsKeepTheirSpecificResponseInsteadOfServerFailure() throws Exception {
        ResponseEntity<Object> response = new ValidationExceptionHandler().handleValidationException(
                new ValidationException(List.of("O campo nome está vazio.", "E-mail inválido."))
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiErrorResponse body = (ApiErrorResponse) Objects.requireNonNull(response.getBody());
        assertEquals("VALIDATION_ERROR", body.code());
        assertEquals(2, body.errors().size());
        assertEquals("O campo nome está vazio.", body.errors().get(0).message());

        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        JsonNode json = mapper.readTree(mapper.writeValueAsString(body));
        assertEquals(400, json.get("status").asInt());
        assertTrue(json.get("timestamp").isTextual());
        assertTrue(json.get("errors").get(0).has("message"));
        assertEquals(6, json.size());
    }

    @Test
    void unexpectedErrorsUseServerFailureResponse() {
        ResponseEntity<Object> response = new GlobalExceptionHandler()
                .handleUnexpectedException(new IllegalStateException("Unexpected failure"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ApiErrorResponse body = (ApiErrorResponse) Objects.requireNonNull(response.getBody());
        assertEquals("INTERNAL_SERVER_ERROR", body.code());
        assertEquals(500, body.status());
        assertTrue(body.errors().isEmpty());
    }

    @Test
    void nullPointerErrorsAreReportedAsBadRequests() {
        ResponseEntity<Object> response = new GlobalExceptionHandler()
                .handleNullPointerException(new NullPointerException("Missing input"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiErrorResponse body = (ApiErrorResponse) Objects.requireNonNull(response.getBody());
        assertEquals("BAD_REQUEST", body.code());
        assertEquals("Dados inválidos", body.title());
    }

    @Test
    void successResponsesUseDataAndMetaFields() {
        ApiResponse<Long> response = new ApiResponse<>(42L);

        assertEquals(42L, response.data());
        assertNull(response.meta());
    }

    @Test
    void successResponseSerializesUsingTheStandardEnvelope() throws Exception {
        JsonNode json = new ObjectMapper().readTree(
                new ObjectMapper().writeValueAsString(new ApiResponse<>(42L))
        );

        assertEquals(42L, json.get("data").asLong());
        assertEquals(1, json.size());
        assertTrue(json.get("meta") == null);
    }

    @Test
    void collectionResponsesIncludeSummaryMetadata() throws Exception {
        JsonNode json = new ObjectMapper().readTree(
                new ObjectMapper().writeValueAsString(ApiResponse.collection(List.of(42L, 43L)))
        );

        assertTrue(json.get("data").isArray());
        assertEquals(0, json.get("meta").get("page").asInt());
        assertEquals(2, json.get("meta").get("size").asInt());
        assertEquals(2, json.get("meta").get("totalItems").asInt());
        assertEquals(1, json.get("meta").get("totalPages").asInt());
    }
}
