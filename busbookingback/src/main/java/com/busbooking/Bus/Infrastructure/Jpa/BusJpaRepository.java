package com.busbooking.Bus.Infrastructure.Jpa;

import com.busbooking.Bus.Domain.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface BusJpaRepository extends JpaRepository<BusEntity, Integer> {


    BusEntity findBusEntityByDateIsAndOriginIsAndDestinationIsAndHourIs(Date date, String origin, String destination, String hour);

}
