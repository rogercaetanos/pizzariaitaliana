package com.itb.mif3an.pizzariaitaliana.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;

@RestControllerAdvice
public class AppExceptionHandler {

    private final ZoneId zoneBrasil = ZoneId.of("America/Sao_Paulo");


    private ResponseEntity<Object> buildErrorResponse (Exception e, HttpStatus status, String userMessage) {

        return null;
    }

}
