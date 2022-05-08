package com.busbooking.Bus.Domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface BusService {

    void createBus(BusEntity busEntity);

    BusEntity findBusById(int id);

    List<BusEntity> findAllBuses();

    void deleteBusById(int id);

    //BusEntity findBusToBookAndDecreaseSeat(Date date, String city, String hour);
    void decreaseAvailableSeats(BusEntity busEntity);

    public BusEntity findBusToBook(Date date, String origin, String destination, String hour);

    public List<BusEntity> getAvailableBookings(HashMap<String, Object> params);
}
