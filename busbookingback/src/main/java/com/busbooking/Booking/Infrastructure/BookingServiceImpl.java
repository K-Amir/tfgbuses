package com.busbooking.Booking.Infrastructure;

import com.busbooking.Booking.Domain.BookingEntity;
import com.busbooking.Booking.Domain.BookingService;
import com.busbooking.Booking.Infrastructure.Jpa.BookingJpaRepository;
import com.busbooking.Bus.Domain.BusEntity;
import com.busbooking.Bus.Domain.BusService;
import com.busbooking.ErrorHandling.BusIncidenceExpcetion;
import com.busbooking.ErrorHandling.NoSeatsAvailableException;
import com.busbooking.Incidence.Domain.IncidenceEntity;
import com.busbooking.Incidence.Domain.IncidenceService;
import com.busbooking.Mail.Domain.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private BookingJpaRepository bookingRepo;
    private BusService busService;
    private MailService mailService;
    private IncidenceService incidenceService;


    @Override
    public BookingEntity createBooking(BookingEntity bookingEntity) {

        BusEntity busEntity = busService.findBusToBook(bookingEntity.getDate(), bookingEntity.getOrigin(), bookingEntity.getDestination(), bookingEntity.getHour());

        bookingEntity.setBusEntity(busEntity);

        if(busEntity.getAvailableSeats()<=0){
            throw new NoSeatsAvailableException("The book seats are already full");
        }

        BookingEntity bookingEntitySaved = bookingRepo.saveAndFlush(bookingEntity);

        IncidenceEntity incidence = incidenceService.findIncidenceByBusId(busEntity.getId());

        if (busHasIncidences(incidence)) {
            mailService.sendSuccessEmail(bookingEntity);
            throw new BusIncidenceExpcetion("Bus has an incidence:  " + incidence.getReason());
        }

        mailService.sendSuccessEmail(bookingEntitySaved);
        busService.decreaseAvailableSeats(busEntity);

        return bookingEntitySaved;
    }

    @Override
    public BookingEntity findBookingById(int id) {
        return bookingRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id: " + id + " not found :("));
    }

    @Override
    public void deleteBookingById(int id) {
        try {
            bookingRepo.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Could not delete booking with id: " + id + " , sorry :(");
        }
    }

    @Override
    public void registerBookingFromWebAndCheckAvailability(BookingEntity bookingEntity) {
        BusEntity busEntity = busService.findBusToBook(bookingEntity.getDate(), bookingEntity.getOrigin(), bookingEntity.getDestination(), bookingEntity.getHour());

        bookingEntity.setBusEntity(busEntity);

        bookingRepo.saveAndFlush(bookingEntity);

        IncidenceEntity incidence = incidenceService.findIncidenceByBusId(busEntity.getId());

        if(busEntity.getAvailableSeats()<=0){
            mailService.sendCancellationEmail(bookingEntity, "Sorry but your travel has been cancelled due to insuficient seats");
            return;
        }


        if (busHasNoIncidences(incidence)) {
            busService.decreaseAvailableSeats(busEntity);
            mailService.sendSuccessEmail(bookingEntity);
            return;
        }

        mailService.sendCancellationEmail(bookingEntity, incidence.getReason());


    }

    @Override
    public List<BookingEntity> findBookingEntitiesByBusEntity_Id(int busId) {
        return bookingRepo.findBookingEntitiesByBusEntity_Id(busId);
    }

    @Override
    public BookingEntity findBookingEntityByEmailIsAndOriginIsAndDestinationIsAndDateIsAndHourIs(String email, String origin, String destination, Date date, String hour) {
        return bookingRepo.findBookingEntityByEmailIsAndOriginIsAndDestinationIsAndDateIsAndHourIs(email, origin, destination, date, hour);
    }



    private boolean busHasNoIncidences(IncidenceEntity incidence) {
        return incidence == null;
    }

    private boolean busHasIncidences(IncidenceEntity incidence) {
        return incidence != null;
    }




}
