package com.busbooking.Incidence.Domain;

import com.busbooking.Bus.Domain.BusEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "incidence")
@Getter
@Setter
public class IncidenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "bus_id")
    private BusEntity busEntity;

    private String reason;
}
