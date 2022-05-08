package com.busbooking.ErrorHandling;

public class NoSeatsAvailableException extends RuntimeException{
    public NoSeatsAvailableException(String message){
        super(message);
    }
}
