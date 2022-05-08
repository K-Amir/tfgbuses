package com.busbooking.Bus.Domain;

import com.busbooking.Bus.Application.Dto.BusInputDto;
import com.busbooking.Bus.Application.Dto.BusOutputDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T19:13:52+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class BusMapperImpl implements BusMapper {

    @Override
    public BusEntity inputDtoToEntity(BusInputDto busInputDto) {
        if ( busInputDto == null ) {
            return null;
        }

        BusEntity busEntity = new BusEntity();

        busEntity.setArrivalHour( busInputDto.getArrivalHour() );
        busEntity.setPrice( busInputDto.getPrice() );
        busEntity.setHour( busInputDto.getHour() );
        busEntity.setDate( busInputDto.getDate() );
        busEntity.setOrigin( busInputDto.getOrigin() );
        busEntity.setDestination( busInputDto.getDestination() );
        busEntity.setAvailableSeats( busInputDto.getAvailableSeats() );

        return busEntity;
    }

    @Override
    public List<BusOutputDto> inputListToOutput(List<BusEntity> busEntityList) {
        if ( busEntityList == null ) {
            return null;
        }

        List<BusOutputDto> list = new ArrayList<BusOutputDto>( busEntityList.size() );
        for ( BusEntity busEntity : busEntityList ) {
            list.add( entityToOutput( busEntity ) );
        }

        return list;
    }

    @Override
    public BusOutputDto entityToOutput(BusEntity busEntity) {
        if ( busEntity == null ) {
            return null;
        }

        BusOutputDto busOutputDto = new BusOutputDto();

        busOutputDto.setId( busEntity.getId() );
        busOutputDto.setHour( busEntity.getHour() );
        busOutputDto.setDate( busEntity.getDate() );
        busOutputDto.setArrivalHour( busEntity.getArrivalHour() );
        busOutputDto.setPrice( busEntity.getPrice() );
        busOutputDto.setOrigin( busEntity.getOrigin() );
        busOutputDto.setDestination( busEntity.getDestination() );
        busOutputDto.setAvailableSeats( busEntity.getAvailableSeats() );

        return busOutputDto;
    }
}
