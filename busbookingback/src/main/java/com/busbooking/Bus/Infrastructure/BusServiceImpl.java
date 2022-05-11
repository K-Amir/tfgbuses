package com.busbooking.Bus.Infrastructure;


import com.busbooking.Bus.Domain.BusEntity;
import com.busbooking.Bus.Domain.BusService;
import com.busbooking.Bus.Infrastructure.Jpa.BusJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class BusServiceImpl implements BusService {
    private BusJpaRepository busRepo;
    private EntityManager entityManager;
    @Override
    public void createBus(BusEntity busEntity) {
        busRepo.saveAndFlush(busEntity);

    }

    @Override
    public BusEntity findBusById(int id) {
        return busRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Bus with id: " + id + " not found :("));
    }

    @Override
    public List<BusEntity> findAllBuses() {
        return busRepo.findAll();
    }

    @Override
    public void deleteBusById(int id) {
        try {
            busRepo.deleteById(id);

        } catch (Exception e) {
            throw new EntityNotFoundException("Could not delete bus with id: " + id + " , sorry :(");
        }
    }

    @Override
    public BusEntity findBusToBook(Date date, String origin, String destination, String hour) {
        BusEntity busEntity = busRepo.findBusEntityByDateIsAndOriginIsAndDestinationIsAndHourIs(date, origin, destination, hour);
        if (busEntity == null) {
            throw new EntityNotFoundException("There's no bus for the specified city, date and hour ");
        }
        return busEntity;
    }

    @Override
    public List<BusEntity> getAvailableBookings(HashMap<String, Object> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BusEntity> query = cb.createQuery(BusEntity.class);
        Root<BusEntity> root = query.from(BusEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        params.forEach((key,value)->{
            switch (key){
                case "lowerDate":
                    try {
                        predicates.add(cb.greaterThanOrEqualTo(root.get("date"), (Date)new SimpleDateFormat("dd/MM/yyyy").parse((String) value)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "upperDate":
                    try {
                        predicates.add(cb.lessThanOrEqualTo(root.get("date"), (Date)new SimpleDateFormat("dd/MM/yyyy").parse((String) value)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "lowerHour":
                    predicates.add(cb.greaterThanOrEqualTo(root.get("hour"), (String)value));
                    break;
                case "upperHour":
                    predicates.add(cb.lessThan(root.get("hour"), (String)value));
                    break;


                case "origin":
                    predicates.add(cb.equal(root.get("origin"), (String)value));
                    break;
                case "destination":
                    predicates.add(cb.equal(root.get("destination"), (String)value));
                    break;

            }
        });


        predicates.add(cb.greaterThan(root.get("availableSeats"), 0));

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));


        return entityManager
                .createQuery(query)
                .getResultList();

    }


    @Override
    public void decreaseAvailableSeats(BusEntity busEntity) {
        busEntity.setAvailableSeats(busEntity.getAvailableSeats() - 1);
        busRepo.saveAndFlush(busEntity);
    }


}
