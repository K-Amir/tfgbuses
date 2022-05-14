package com.busbooking.Incidence.Infrastructure;

import com.busbooking.Booking.Domain.BookingService;
import com.busbooking.Bus.Domain.BusEntity;
import com.busbooking.Bus.Domain.BusService;
import com.busbooking.Incidence.Domain.IncidenceEntity;
import com.busbooking.Incidence.Domain.IncidenceService;
import com.busbooking.Mail.Domain.MailService;
import com.busbooking.Incidence.Infrastructure.Jpa.IncidenceJpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public record IncidenceServiceImpl(
        IncidenceJpaRepository incidenceRepo,
        BusService busService,
        MailService mailService

) implements IncidenceService {
    @Override
    public void save(IncidenceEntity incidenceEntity, int busId) {

        BusEntity busEntity = busService().findBusById(busId);

        var incidence = incidenceRepo.findIncidenceByBusEntity_Id(busId);
        if (incidence != null) {
            throw  new EntityNotFoundException("Can not add another incidence to the same busId");
        };

        incidenceEntity.setBusEntity(busEntity);


        incidenceRepo.save(incidenceEntity);
    }


    @Override
    public void deleteById(int id) {
        try {
            incidenceRepo.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Could not delete incidence with id: " + id + " , sorry :(");
        }
    }

    @Override
    public IncidenceEntity findById(int id) {
        return incidenceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Incidence with id: " + id + " not found :("));
    }

    @Override
    public List<IncidenceEntity> findAll() {
        return incidenceRepo.findAll();
    }

    @Override
    public IncidenceEntity findIncidenceByBusId(int id) {
        return incidenceRepo.findIncidenceByBusEntity_Id(id);
    }


}
