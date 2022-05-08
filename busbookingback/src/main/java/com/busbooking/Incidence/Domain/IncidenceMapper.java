package com.busbooking.Incidence.Domain;

import com.busbooking.Incidence.Application.Dto.IncidenceInputDto;
import com.busbooking.Incidence.Application.Dto.IncidenceOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IncidenceMapper {

    IncidenceMapper MAPPER = Mappers.getMapper(IncidenceMapper.class);

    IncidenceEntity incidenceInputToEntity(IncidenceInputDto incidenceInputDto);

    @Mapping(source = "busEntity.id", target = "busId")
    IncidenceOutputDto incidenceToOutput(IncidenceEntity incidenceEntity);

    @Mapping(source = "busEntity.id", target = "busId")
    List<IncidenceOutputDto> incidenceToOutputList(List<IncidenceEntity> incidenceEntityList);
}
