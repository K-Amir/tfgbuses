package com.busbooking.Booking.Application.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingOutputDto {
    private String destination;
    private String origin;
    private String name;
    private String phone;
    private String email;
    private String  hour;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String date;
}
