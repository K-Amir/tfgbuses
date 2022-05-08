package com.busbooking.Incidence.Infrastructure.Jpa;

import com.busbooking.Incidence.Domain.IncidenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenceJpaRepository extends JpaRepository<IncidenceEntity, Integer> {
    IncidenceEntity findIncidenceByBusEntity_Id(int busId);
}
