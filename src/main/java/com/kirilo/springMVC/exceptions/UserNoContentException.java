package com.kirilo.springMVC.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "No Content")
public class UserNoContentException extends Exception {
    private static final long serialVUID = Long.MIN_VALUE + 100;

    public UserNoContentException(int id) {
        super("UserNoContentException with id=" + id);
    }
}
