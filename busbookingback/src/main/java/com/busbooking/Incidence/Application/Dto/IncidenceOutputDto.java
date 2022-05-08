package com.busbooking.Incidence.Application.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidenceOutputDto {
    private Integer id;
    private Integer busId;
    private String reason;
}
