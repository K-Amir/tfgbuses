package com.busbooking.ErrorHandling;

public class PasswordDoesNotMatchException extends RuntimeException{
    public PasswordDoesNotMatchException(String msg){
        super(msg);
    }

}
