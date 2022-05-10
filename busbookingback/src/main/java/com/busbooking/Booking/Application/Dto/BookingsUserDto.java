package com.busbooking.Booking.Application.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingsUserDto {
    private String arrivalHour;
    private String price;
    private String hour;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private String origin;
    private String destination;
    private String jwtQr;

}
