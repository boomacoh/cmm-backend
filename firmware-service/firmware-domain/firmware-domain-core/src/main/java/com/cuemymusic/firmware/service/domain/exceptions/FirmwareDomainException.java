package com.cuemymusic.firmware.service.domain.exceptions;

import com.cuemymusic.user.service.domain.exceptions.DomainException;

public class FirmwareDomainException extends DomainException {

    public FirmwareDomainException(String message) {
        super(message);
    }

    public FirmwareDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
