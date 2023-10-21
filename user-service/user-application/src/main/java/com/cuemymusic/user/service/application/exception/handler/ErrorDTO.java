package com.cuemymusic.user.service.application.exception.handler;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private  String code;
    private  String message;
    private Integer statusCode;
}
