package com.busbooking.Mail.Application.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MailOutputDto {

    private String destinationCity;

    private String email;

    private String bookingHour;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date bookingDate;
}
