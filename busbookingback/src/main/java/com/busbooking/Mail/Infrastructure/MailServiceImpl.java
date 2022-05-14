package com.busbooking.Mail.Infrastructure;


import com.busbooking.Booking.Domain.BookingEntity;
import com.busbooking.Incidence.Domain.IncidenceEntity;
import com.busbooking.Mail.Domain.MailEntity;
import com.busbooking.Mail.Domain.MailService;
import com.busbooking.Mail.Infrastructure.Jpa.MailJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Component
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private JavaMailSender javaMailSender;
    private MailJpaRepository mailRepo;


    @Async("asyncExecutor")
    public CompletableFuture<Void> sendCancellationMails(List<BookingEntity> bookingEntityList, IncidenceEntity incidenceEntity) throws InterruptedException {
        for (BookingEntity bookingEntity : bookingEntityList) {
            sendCancellationEmail(bookingEntity, incidenceEntity.getReason());
        }
        return null;
    }



    public void sendSuccessEmail(BookingEntity bookingEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(bookingEntity.getUsersEntity().getEmail());

        message.setFrom("bustravelinformation@gmail.com");
        message.setSubject("Good news ! Your booking to " + bookingEntity.getBusEntity().getDestination() + " on " + formatDate(bookingEntity.getBusEntity().getDate()) + " at " + bookingEntity.getBusEntity().getHour() + " has been registered successfully.");
        String mailContent = "Thanks for trusting in BusTravel! \nIn case of any incidence or cancellation you will be informed as well too " +
                "\n\n\n\n\n\n\nYour confirmation details are the followings: " +
                "\nBooking Id " + bookingEntity.getId() + "\nPassenger " + bookingEntity.getUsersEntity().getName() + " " + bookingEntity.getUsersEntity().getSurname()
                +"\nOrigin " + bookingEntity.getBusEntity().getOrigin() + "\nDestination " + bookingEntity.getBusEntity().getDestination() + "\nDepart hour and date " +
                formatDate(bookingEntity.getBusEntity().getDate()) + " " + bookingEntity.getBusEntity().getHour();
        message.setText(mailContent);

        registerMailEntity(MailEntity.builder()
                .mailTo(bookingEntity.getUsersEntity().getEmail())
                .mailFrom("bustravelinformation@gmail.com")
                .mailMessage(mailContent)
                .build());

        javaMailSender.send(message);
    }


    @Override
    public void sendCancellationEmail(BookingEntity bookingEntity, String reason) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bustravelinformation@gmail.com");
        message.setTo(bookingEntity.getUsersEntity().getEmail());
        message.setSubject("Bad news. Your booking to " + bookingEntity.getBusEntity().getDestination() + "  on " + formatDate(bookingEntity.getBusEntity().getDate()) + " at " + bookingEntity.getBusEntity().getHour() + " has been cancelled.");
        message.setText(reason);

        registerMailEntity(MailEntity.builder()
                .mailTo(bookingEntity.getUsersEntity().getEmail())
                .mailFrom("bustravelinformation@gmail.com")
                .mailMessage(reason)
                .build());

        javaMailSender.send(message);
    }

    @Override
    public MailEntity registerMailEntity(MailEntity mailEntity) {
        return mailRepo.save(mailEntity);
    }



    public String formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
