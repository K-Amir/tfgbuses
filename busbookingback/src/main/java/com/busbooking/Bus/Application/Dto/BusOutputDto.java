package com.busbooking.Bus.Application.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BusOutputDto {
    private Integer id;

    private String hour;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private String arrivalHour;

    private String price;

    private String origin;

    private String destination;

    private Integer availableSeats;
}
