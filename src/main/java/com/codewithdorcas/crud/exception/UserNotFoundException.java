package com.codewithdorcas.crud.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("no user with id " + id);
    }

}
