package com.kalthko.employee_crud.exception;

public class CountryServiceException extends RuntimeException {
    public CountryServiceException(String message,Throwable cause) {

        super(message,cause);
    }
}
