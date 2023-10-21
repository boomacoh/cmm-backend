package com.cuemymusic.user.service.domain.exception;

import com.cuemymusic.user.service.domain.exceptions.DomainException;

public class UserDomainException extends DomainException {

    public UserDomainException(String message) {
        super(message);
    }

    public UserDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
