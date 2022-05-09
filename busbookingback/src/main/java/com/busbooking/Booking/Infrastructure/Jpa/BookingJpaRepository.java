package com.busbooking.Booking.Infrastructure.Jpa;

import com.busbooking.Booking.Domain.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, Integer> {
    List<BookingEntity> findBookingEntitiesByBusEntity_Id(int busId);


}
