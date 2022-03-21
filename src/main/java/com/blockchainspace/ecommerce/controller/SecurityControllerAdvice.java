package com.blockchainspace.ecommerce.controller;

import com.blockchainspace.ecommerce.ProductManagementException;
import com.blockchainspace.ecommerce.dto.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class SecurityControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handle(UnauthenticatedException e) {
        log.error("unauthorized", e);
        return ErrorResponse.builder().reason("Unauthorized").message(e.getMessage()).build();
    }

    @ExceptionHandler(ProductManagementException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handle(AuthorizationException e) {
        log.error("forbidden", e);
        return ErrorResponse.builder().reason("Forbidden").message(e.getMessage()).build();
    }

}
