package com.s3.individual.s3individual.Business.exception;

public class EmailNotFoundException extends CustomException{
    public EmailNotFoundException(){
        super("Email not found");
    };
}
