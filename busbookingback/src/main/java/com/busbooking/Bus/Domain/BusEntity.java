package com.busbooking.Bus.Domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "bus")
@Getter
@Setter
public class BusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Just info
    private String arrivalHour;

    private String price;

    private String hour;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private String origin;

    private String destination;

    private Integer availableSeats;

}
