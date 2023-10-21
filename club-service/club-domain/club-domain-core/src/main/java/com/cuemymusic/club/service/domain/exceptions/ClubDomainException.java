package com.cuemymusic.club.service.domain.exceptions;

import com.cuemymusic.user.service.domain.exceptions.DomainException;

public class ClubDomainException extends DomainException {

    public ClubDomainException(String message) {
        super(message);
    }

    public ClubDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
