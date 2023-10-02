package com.invisibleprogrammer.invisibletirapi.domain.service;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("E-mail already exists");
    }
}
