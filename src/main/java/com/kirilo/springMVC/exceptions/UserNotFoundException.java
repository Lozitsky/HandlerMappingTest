package com.kirilo.springMVC.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class UserNotFoundException extends Exception {
    private static final long serialVUID = Long.MIN_VALUE + 111;

    public UserNotFoundException(int id) {
        super("UserNotFoundException with id=" + id);
    }
}
