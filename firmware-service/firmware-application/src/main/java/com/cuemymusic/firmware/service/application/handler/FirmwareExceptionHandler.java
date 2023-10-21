package com.cuemymusic.firmware.service.application.handler;


import com.cuemymusic.firmware.service.domain.exceptions.FirmwareDomainException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class FirmwareExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {FirmwareDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(FirmwareDomainException userDomainException){
        log.error(userDomainException.getMessage(),userDomainException);
        return new ErrorDTO().builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(userDomainException.getMessage())
                .build();
    }
    @ResponseBody
    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleException(AccessDeniedException accessDeniedException){
        log.error(accessDeniedException.getMessage(),accessDeniedException);
        return new ErrorDTO().builder()
                .code(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(accessDeniedException.getMessage())
                .build();
    }
    @ResponseBody
    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleException(ExpiredJwtException authException){
        log.error(authException.getMessage(),authException);
        return new ErrorDTO().builder()
                .code(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(authException.getMessage())
                .build();
    }
}
