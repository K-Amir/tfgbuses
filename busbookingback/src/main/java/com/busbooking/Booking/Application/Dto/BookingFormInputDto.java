package com.busbooking.Booking.Application.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Setter
@Getter
public class BookingFormInputDto {

    @NotNull(message = "You must provide a valid busId value, Ex '1'")
    private int busId;

    @NotNull(message = "userEmail must be filled, Ex - 'blahblah@blah.blah'")
    private String userEmail;
}
