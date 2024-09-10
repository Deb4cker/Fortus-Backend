package com.pmnato.fortus.exception.not_found;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException() {
        super("User not found!");
    }
}
