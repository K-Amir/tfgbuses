package com.busbooking.Mail.Infrastructure;


import com.busbooking.Booking.Domain.BookingEntity;
import com.busbooking.Mail.Domain.MailEntity;
import com.busbooking.Mail.Domain.MailService;
import com.busbooking.Mail.Infrastructure.Jpa.MailJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private JavaMailSender javaMailSender;
    private MailJpaRepository mailRepo;
    @PersistenceContext
    private EntityManager entityManager;

    public void sendSuccessEmail(BookingEntity bookingEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(bookingEntity.getUsersEntity().getEmail());
        message.setFrom("virtual.travel.info@gmail.com");
        message.setSubject("Good news ! Your booking to " + bookingEntity.getBusEntity().getDestination() + " on " + formatDate(bookingEntity.getBusEntity().getDate()) + " at " + bookingEntity.getBusEntity().getDate() + " has been registered successfully.");
        message.setText("Thanks for trusting in VirtualTravel! \nIn case of any incidence or cancellation you will be informed as well too");

        registerMailEntity(MailEntity.builder()
                .mailTo(bookingEntity.getUsersEntity().getEmail())
                .mailFrom("virtual.travel.info@gmail.com")
                .mailMessage("Thanks for trusting in VirtualTravel! \nIn case of any incidence or cancellation you will be informed as well too")
                .build());

        javaMailSender.send(message);
    }


    @Override
    public void sendCancellationEmail(BookingEntity bookingEntity, String reason) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("virtual.travel.info@gmail.com");
        message.setTo(bookingEntity.getUsersEntity().getEmail());
        message.setSubject("Bad news :C... Your booking to " + bookingEntity.getBusEntity().getDestination() + "  on " + formatDate(bookingEntity.getBusEntity().getDate()) + " at " + bookingEntity.getBusEntity().getHour() + " has been cancelled.");
        message.setText(reason);

        registerMailEntity(MailEntity.builder()
                .mailTo(bookingEntity.getUsersEntity().getEmail())
                .mailFrom("virtual.travel.info@gmail.com")
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
