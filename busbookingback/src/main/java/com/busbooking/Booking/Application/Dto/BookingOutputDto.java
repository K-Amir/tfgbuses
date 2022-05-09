package com.busbooking.Booking.Application.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingOutputDto {
    private int id;

    private int busId;

    private String userEmail;
}
