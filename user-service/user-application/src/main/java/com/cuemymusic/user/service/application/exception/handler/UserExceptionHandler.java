package com.cuemymusic.user.service.application.exception.handler;


import com.cuemymusic.user.service.domain.exceptions.AuthException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class UserExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {UserDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(UserDomainException userDomainException){
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
    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleException(BadCredentialsException badCredentialsException){
        log.error(badCredentialsException.getMessage(),badCredentialsException);
        return new ErrorDTO().builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .code(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(badCredentialsException.getMessage())
                .build();
    }
    @ResponseBody
    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleException(ExpiredJwtException authException){
        log.error(authException.getMessage(),authException);
        return new ErrorDTO().builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .code(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(authException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {AuthException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorDTO handleException(AuthException authException){
        log.error(authException.getMessage(),authException);
        return new ErrorDTO().builder()
                .statusCode(authException.getCode())
                .code(authException.getMessage())
                .message(authException.getMessage())
                .build();
    }



}
