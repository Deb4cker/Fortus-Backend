package com.pmnato.fortus.service.response;

import java.util.Map;

public record ApiResponse<T>(T data, Map<String, Object> meta) {
    public ApiResponse(T data) {
        this(data, Map.of());
    }
}
