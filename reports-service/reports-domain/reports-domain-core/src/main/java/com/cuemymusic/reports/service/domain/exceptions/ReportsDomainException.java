package com.cuemymusic.reports.service.domain.exceptions;

import com.cuemymusic.user.service.domain.exceptions.DomainException;

public class ReportsDomainException extends DomainException {

    public ReportsDomainException(String message) {
        super(message);
    }

    public ReportsDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
