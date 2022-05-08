package com.busbooking.Booking.Domain;


import com.busbooking.Booking.Application.Dto.BookingFormInputDto;
import com.busbooking.Booking.Application.Dto.BookingOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper MAP = Mappers.getMapper(BookingMapper.class);

    BookingEntity bookingInDtoToEntity(BookingFormInputDto bookingFormInputDto);

    BookingOutputDto bookingEntityToOutput(BookingEntity bookingEntity);
}
