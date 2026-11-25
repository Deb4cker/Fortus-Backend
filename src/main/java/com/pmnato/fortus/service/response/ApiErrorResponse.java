package com.pmnato.fortus.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ApiErrorResponse(
        String title,
        int status,
        String detail,
        String code,
        String timestamp,
        List<ErrorItem> errors
) {
    public static ApiErrorResponse of(HttpStatus status, String detail, String code, List<String> messages) {
        List<ErrorItem> errors = messages.stream()
                .map(message -> new ErrorItem(null, "VALIDATION_ERROR", message))
                .toList();
        return new ApiErrorResponse(
                titleFor(status),
                status.value(),
                detail,
                code,
                Instant.now().toString(),
                errors
        );
    }

    private static String titleFor(HttpStatus status) {
        return switch (status) {
            case BAD_REQUEST -> "Dados inválidos";
            case UNAUTHORIZED -> "Não autenticado";
            case FORBIDDEN -> "Acesso negado";
            case NOT_FOUND -> "Não encontrado";
            default -> "Erro interno do servidor";
        };
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record ErrorItem(String field, String code, String message) {
    }
}
