package com.s3.individual.s3individual.Business.exception;

public class CustomException extends RuntimeException{
    public CustomException(String errorMessage){
        super(errorMessage);
    }
}
