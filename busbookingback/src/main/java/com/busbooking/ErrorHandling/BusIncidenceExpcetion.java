package com.busbooking.ErrorHandling;

public class BusIncidenceExpcetion extends RuntimeException {
    public BusIncidenceExpcetion(String msg) {
        super(msg);
    }
}
