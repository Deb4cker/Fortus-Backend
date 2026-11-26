package com.pmnato.fortus.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record ApiResponse<T>(T data, @JsonInclude(JsonInclude.Include.NON_NULL) Metadata meta) {
    public ApiResponse(T data) {
        this(data, null);
    }

    public static <T> ApiResponse<List<T>> collection(List<T> data) {
        int size = data.size();
        return new ApiResponse<>(
                data,
                new Metadata(
                    0, 
                    size, 
                    size, 
                    size == 0 ? 0 : 1 
                )
        );
    }
}
