package com.busbooking.Booking.Application.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyingInputDto {

    private int busId;
    private String userEmail;
    private int amount; //cents
    private String id;

}
