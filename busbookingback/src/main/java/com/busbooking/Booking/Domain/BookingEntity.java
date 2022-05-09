package com.busbooking.Booking.Domain;

import com.busbooking.Bus.Domain.BusEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "booking")
@Getter
@Setter
public class BookingEntity {
    private String name;

    private String phone;

    @Id
    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private String hour;

    private String origin;


    private String destination;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusEntity busEntity;


}
