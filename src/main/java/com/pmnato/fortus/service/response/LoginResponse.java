package com.pmnato.fortus.service.response;

import org.springframework.http.HttpStatus;

public record LoginResponse(String message, HttpStatus httpStatus) implements Response {}
