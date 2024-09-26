package com.pmnato.fortus.utils;

import com.pmnato.fortus.exception.security.SecurityException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public final class Encryptor
{
    private Encryptor(){}

    public static String sha256Of(String input, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            messageDigest.update(saltBytes);
            byte[] hashBytes = messageDigest.digest(input.getBytes());

            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("SHA-256 algorithm not found" + e.getMessage());
        }
    }

    public static EncryptedInputData getEncryptData(String input) {
        byte[] saltBytes = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(saltBytes);
        String salt = Base64.getEncoder().encodeToString(saltBytes);
        String hash = sha256Of(input, salt);
        return new EncryptedInputData(salt, hash);
    }
}
