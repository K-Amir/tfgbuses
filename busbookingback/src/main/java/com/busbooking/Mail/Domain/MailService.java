package com.busbooking.Mail.Domain;

import com.busbooking.Booking.Domain.BookingEntity;
import com.busbooking.Incidence.Domain.IncidenceEntity;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MailService {

    void sendSuccessEmail(BookingEntity bookingEntity);

    @Async("asyncExecutor")
    CompletableFuture<Void> sendCancellationMails(List<BookingEntity> bookingEntityList, IncidenceEntity incidenceEntity) throws InterruptedException;

    void sendCancellationEmail(BookingEntity bookingEntity, String reason);

    MailEntity registerMailEntity(MailEntity mailEntity);


}
