package com.busbooking.Booking.Domain;

import com.busbooking.Booking.Application.Dto.BookingFormInputDto;

import java.util.Date;
import java.util.List;

public interface BookingService {

    BookingEntity createBooking(BookingEntity bookingEntity, BookingFormInputDto bookingFormInputDto);

    BookingEntity findBookingById(int id);

    void deleteBookingById(int id);

    List<BookingEntity> findBookingEntitiesByBusEntity_Id(int busId);

}
