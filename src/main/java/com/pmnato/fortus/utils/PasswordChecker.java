package com.pmnato.fortus.utils;

import com.pmnato.fortus.entity.User;

public final class PasswordChecker {
    private PasswordChecker() {}

    public static boolean isCorrectPassword(User user, String passwordInput) {
        String comparativePassword = Encryptor.sha256Of(passwordInput, user.getSalt());
        return user.getPassword().equals(comparativePassword);
    }
}
