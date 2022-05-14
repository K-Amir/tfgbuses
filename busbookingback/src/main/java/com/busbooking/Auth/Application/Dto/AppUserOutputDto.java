package com.busbooking.Auth.Application.Dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AppUserOutputDto {


    private String email;
    private String name;
    private String surname;
}
