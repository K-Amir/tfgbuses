package com.busbooking.Auth.Application.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AppUserInputDto {
    @NotEmpty(message = "Email must be filled, Ex - 'jhon@doe.com'")
    private String email;

    @NotEmpty(message = "Password must be filled, Ex - '123456'")
    private String password;

    @NotEmpty(message = "Name must be filled, Ex - 'John'")
    private String name;

    @NotEmpty(message = "Surname must be filled, Ex - 'Doe'")
    private String surname;

}
