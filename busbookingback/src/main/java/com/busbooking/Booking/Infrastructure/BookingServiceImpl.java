package com.busbooking.Booking.Infrastructure;

import com.busbooking.Auth.Domain.AuthService;
import com.busbooking.Booking.Application.Dto.BookingFormInputDto;
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

    private AuthService authService;


    @Override
    public BookingEntity createBooking(BookingEntity bookingEntity, BookingFormInputDto bookingFormInputDto) {

        BusEntity busEntity = busService.findBusById(bookingFormInputDto.getBusId());

        if (busEntity.getAvailableSeats() <= 0) {
            throw new NoSeatsAvailableException("The book seats are already full");
        }

        bookingEntity.setBusEntity(busEntity);

        bookingEntity.setUsersEntity(authService.getByEmail(bookingFormInputDto.getUserEmail()));

        IncidenceEntity incidence = incidenceService.findIncidenceByBusId(busEntity.getId());

        if (busHasIncidences(incidence)) {
            mailService.sendSuccessEmail(bookingEntity);
            throw new BusIncidenceExpcetion("Bus has an incidence:  " + incidence.getReason());
        }

        BookingEntity bookingEntitySaved = bookingRepo.saveAndFlush(bookingEntity);

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
    public List<BookingEntity> findBookingEntitiesByBusEntity_Id(int busId) {
        return bookingRepo.findBookingEntitiesByBusEntity_Id(busId);
    }


    private boolean busHasNoIncidences(IncidenceEntity incidence) {
        return incidence == null;
    }

    private boolean busHasIncidences(IncidenceEntity incidence) {
        return incidence != null;
    }


}
