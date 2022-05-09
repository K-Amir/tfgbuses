package com.busbooking.Booking.Domain;

import com.busbooking.Booking.Application.Dto.BookingFormInputDto;
import com.busbooking.Booking.Application.Dto.BookingOutputDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-09T22:56:26+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingEntity bookingInDtoToEntity(BookingFormInputDto bookingFormInputDto) {
        if ( bookingFormInputDto == null ) {
            return null;
        }

        BookingEntity bookingEntity = new BookingEntity();

        return bookingEntity;
    }

    @Override
    public BookingOutputDto bookingEntityToOutput(BookingEntity bookingEntity) {
        if ( bookingEntity == null ) {
            return null;
        }

        BookingOutputDto bookingOutputDto = new BookingOutputDto();

        bookingOutputDto.setId( bookingEntity.getId() );

        return bookingOutputDto;
    }
}
