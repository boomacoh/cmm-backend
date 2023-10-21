package com.cuemymusic.user.service.domain.exceptions;

public class AuthException extends RuntimeException{
    int code;
    public AuthException(String message, int code){
        super(message);
        this.code = code;
    }
    public Integer getCode(){
        return code;
    }
}
