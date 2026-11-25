package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.service.response.ApiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobalExceptionHandlerTest {

    @Test
    void validationErrorsKeepTheirSpecificResponseInsteadOfServerFailure() throws Exception {
        ResponseEntity<Object> response = new ValidationExceptionHandler().handleValidationException(
                new ValidationException(List.of("The field name is empty.", "Invalid email address."))
        );

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertEquals(List.of("The field name is empty.", "Invalid email address."), body.data());
        assertEquals(Map.of(), body.meta());

        JsonNode json = new ObjectMapper().readTree(new ObjectMapper().writeValueAsString(body));
        assertTrue(json.get("data").isArray());
        assertTrue(json.get("meta").isObject());
        assertEquals(2, json.size());
    }

    @Test
    void unexpectedErrorsUseServerFailureResponse() {
        ResponseEntity<Object> response = new GlobalExceptionHandler()
                .handleUnexpectedException(new IllegalStateException("Unexpected failure"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertEquals("Server died", body.data());
        assertEquals(Map.of(), body.meta());
    }

    @Test
    void nullPointerErrorsAreReportedAsBadRequests() {
        ResponseEntity<Object> response = new GlobalExceptionHandler()
                .handleNullPointerException(new NullPointerException("Missing input"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertEquals("Null parameter on args.", body.data());
        assertEquals(Map.of(), body.meta());
    }

    @Test
    void successResponsesUseDataAndMetaFields() {
        ApiResponse<Long> response = new ApiResponse<>(42L);

        assertEquals(42L, response.data());
        assertEquals(Map.of(), response.meta());
    }

    @Test
    void successResponseSerializesUsingTheStandardEnvelope() throws Exception {
        JsonNode json = new ObjectMapper().readTree(
                new ObjectMapper().writeValueAsString(new ApiResponse<>(42L))
        );

        assertEquals(42L, json.get("data").asLong());
        assertEquals(2, json.size());
        assertTrue(json.get("meta").isObject());
        assertEquals(0, json.get("meta").size());
    }
}
