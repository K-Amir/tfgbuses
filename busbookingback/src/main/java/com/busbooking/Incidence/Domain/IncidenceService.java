package com.busbooking.Incidence.Domain;

import java.util.List;

public interface IncidenceService {

    void save(IncidenceEntity incidenceEntity, int busId);

    void deleteById(int id);

    IncidenceEntity findById(int id);

    List<IncidenceEntity> findAll();

    IncidenceEntity findIncidenceByBusId(int id);
}
