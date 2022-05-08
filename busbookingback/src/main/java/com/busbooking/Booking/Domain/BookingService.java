package com.busbooking.Booking.Domain;

import java.util.Date;
import java.util.List;

public interface BookingService {

    BookingEntity createBooking(BookingEntity bookingEntity);

    BookingEntity findBookingById(int id);

    void deleteBookingById(int id);

    void registerBookingFromWebAndCheckAvailability(BookingEntity bookingEntity);

    List<BookingEntity> findBookingEntitiesByBusEntity_Id(int busId);

    BookingEntity findBookingEntityByEmailIsAndOriginIsAndDestinationIsAndDateIsAndHourIs(String email, String origin, String destination, Date date, String hour);
}
