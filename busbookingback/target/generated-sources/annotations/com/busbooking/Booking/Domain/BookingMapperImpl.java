package com.busbooking.Booking.Domain;

import com.busbooking.Booking.Application.Dto.BookingFormInputDto;
import com.busbooking.Booking.Application.Dto.BookingOutputDto;
import java.text.SimpleDateFormat;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T18:47:56+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingEntity bookingInDtoToEntity(BookingFormInputDto bookingFormInputDto) {
        if ( bookingFormInputDto == null ) {
            return null;
        }

        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setName( bookingFormInputDto.getName() );
        bookingEntity.setPhone( bookingFormInputDto.getPhone() );
        bookingEntity.setEmail( bookingFormInputDto.getEmail() );
        bookingEntity.setDate( bookingFormInputDto.getDate() );
        bookingEntity.setHour( bookingFormInputDto.getHour() );
        bookingEntity.setOrigin( bookingFormInputDto.getOrigin() );
        bookingEntity.setDestination( bookingFormInputDto.getDestination() );

        return bookingEntity;
    }

    @Override
    public BookingOutputDto bookingEntityToOutput(BookingEntity bookingEntity) {
        if ( bookingEntity == null ) {
            return null;
        }

        BookingOutputDto bookingOutputDto = new BookingOutputDto();

        bookingOutputDto.setDestination( bookingEntity.getDestination() );
        bookingOutputDto.setOrigin( bookingEntity.getOrigin() );
        bookingOutputDto.setName( bookingEntity.getName() );
        bookingOutputDto.setPhone( bookingEntity.getPhone() );
        bookingOutputDto.setEmail( bookingEntity.getEmail() );
        bookingOutputDto.setHour( bookingEntity.getHour() );
        if ( bookingEntity.getDate() != null ) {
            bookingOutputDto.setDate( new SimpleDateFormat().format( bookingEntity.getDate() ) );
        }

        return bookingOutputDto;
    }
}
