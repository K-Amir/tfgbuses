package com.busbooking.Incidence.Domain;

import com.busbooking.Bus.Domain.BusEntity;
import com.busbooking.Incidence.Application.Dto.IncidenceInputDto;
import com.busbooking.Incidence.Application.Dto.IncidenceOutputDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-09T22:56:26+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class IncidenceMapperImpl implements IncidenceMapper {

    @Override
    public IncidenceEntity incidenceInputToEntity(IncidenceInputDto incidenceInputDto) {
        if ( incidenceInputDto == null ) {
            return null;
        }

        IncidenceEntity incidenceEntity = new IncidenceEntity();

        incidenceEntity.setReason( incidenceInputDto.getReason() );

        return incidenceEntity;
    }

    @Override
    public IncidenceOutputDto incidenceToOutput(IncidenceEntity incidenceEntity) {
        if ( incidenceEntity == null ) {
            return null;
        }

        IncidenceOutputDto incidenceOutputDto = new IncidenceOutputDto();

        incidenceOutputDto.setBusId( incidenceEntityBusEntityId( incidenceEntity ) );
        incidenceOutputDto.setId( incidenceEntity.getId() );
        incidenceOutputDto.setReason( incidenceEntity.getReason() );

        return incidenceOutputDto;
    }

    @Override
    public List<IncidenceOutputDto> incidenceToOutputList(List<IncidenceEntity> incidenceEntityList) {
        if ( incidenceEntityList == null ) {
            return null;
        }

        List<IncidenceOutputDto> list = new ArrayList<IncidenceOutputDto>( incidenceEntityList.size() );
        for ( IncidenceEntity incidenceEntity : incidenceEntityList ) {
            list.add( incidenceToOutput( incidenceEntity ) );
        }

        return list;
    }

    private Integer incidenceEntityBusEntityId(IncidenceEntity incidenceEntity) {
        if ( incidenceEntity == null ) {
            return null;
        }
        BusEntity busEntity = incidenceEntity.getBusEntity();
        if ( busEntity == null ) {
            return null;
        }
        Integer id = busEntity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
