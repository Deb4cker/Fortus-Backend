package com.pmnato.fortus.service.request;

public record LoginRequest(String email, String password) implements Request{}
