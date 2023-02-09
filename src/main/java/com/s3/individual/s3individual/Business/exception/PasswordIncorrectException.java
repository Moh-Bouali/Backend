package com.s3.individual.s3individual.Business.exception;

public class PasswordIncorrectException extends CustomException{
    public PasswordIncorrectException(){
        super("Incorrect password");
    };
}
