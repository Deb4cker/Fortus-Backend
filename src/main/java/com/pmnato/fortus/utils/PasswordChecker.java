package com.pmnato.fortus.utils;

import com.pmnato.fortus.entity.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PasswordChecker {
    public boolean isCorrectPassword(User user, String passwordInput) {
        String comparativePassword = Encryptor.sha256Of(passwordInput, user.getSalt());
        return user.getPassword().equals(comparativePassword);
    }
}
