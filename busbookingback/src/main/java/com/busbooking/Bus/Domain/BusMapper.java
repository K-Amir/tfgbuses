package com.busbooking.Bus.Domain;


import com.busbooking.Bus.Application.Dto.BusInputDto;
import com.busbooking.Bus.Application.Dto.BusOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BusMapper {
    BusMapper MAP = Mappers.getMapper(BusMapper.class);


    BusEntity inputDtoToEntity(BusInputDto busInputDto);

    List<BusOutputDto> inputListToOutput(List<BusEntity> busEntityList);

    BusOutputDto entityToOutput(BusEntity busEntity);
}
