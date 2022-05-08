package com.busbooking.Mail.Domain;

import com.busbooking.Booking.Domain.BookingEntity;

import java.util.HashMap;
import java.util.List;

public interface MailService {

    void sendSuccessEmail(BookingEntity bookingEntity);

    void sendCancellationEmail(BookingEntity bookingEntity, String reason);

    MailEntity registerMailEntity(MailEntity mailEntity);


}
