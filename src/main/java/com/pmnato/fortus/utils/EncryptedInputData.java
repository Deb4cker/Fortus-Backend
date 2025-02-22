package com.pmnato.fortus.utils;

public record EncryptedInputData(String salt, String hashedPassword) { }
