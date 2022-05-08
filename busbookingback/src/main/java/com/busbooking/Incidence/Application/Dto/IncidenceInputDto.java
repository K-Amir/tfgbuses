package com.busbooking.Incidence.Application.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IncidenceInputDto {

  @NotNull(message = "You must provide a valid bus_id, Ex 1")
  private Integer bus_id;

  @NotNull(message = "You must provide a valid reason value, Ex 'Bad weather conditions'")
  private String reason;
}
