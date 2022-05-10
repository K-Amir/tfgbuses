package com.busbooking.Booking.Domain;


import com.busbooking.Booking.Application.Dto.BookingFormInputDto;
import com.busbooking.Booking.Application.Dto.BookingOutputDto;
import com.busbooking.Booking.Application.Dto.BookingsUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookingMapper {
    BookingMapper MAP = Mappers.getMapper(BookingMapper.class);

    BookingEntity bookingInDtoToEntity(BookingFormInputDto bookingFormInputDto);

    BookingOutputDto bookingEntityToOutput(BookingEntity bookingEntity);

    @Mapping(source = "busEntity.arrivalHour", target = "arrivalHour")
    @Mapping(source = "busEntity.price", target = "price")
    @Mapping(source = "busEntity.hour", target = "hour")
    @Mapping(source = "busEntity.date", target = "date")
    @Mapping(source = "busEntity.origin", target = "origin")
    @Mapping(source = "busEntity.destination", target = "destination")
    BookingsUserDto bookingEntityToUserOutput(BookingEntity bookingEntity);
}
