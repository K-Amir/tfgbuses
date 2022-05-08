package com.busbooking.Mail.Application.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
public class MailInputDto {
    @NotEmpty(message = "City must be filled, Ex - 'Madrid'")
    private String ciudadDestino;

    @NotEmpty(message = "Email must be filled, Ex - 'jhon@doe.boot'")
    private String email;

    @NotEmpty(message = "Booking hour must be filled, Ex - '16:30'")
    @Pattern(regexp = "([0-1][0-9]|2[0-3]):[0-5][0-9]", message = "Hour does not follow a valid pattern, Ex - '16:30'")
    private String bookingHour;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "You must provide a valid date value, Ex '03-05-2022'")
    private Date bookingDate;
}
