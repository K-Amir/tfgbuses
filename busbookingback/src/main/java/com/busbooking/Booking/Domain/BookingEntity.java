package com.busbooking.Booking.Domain;

import com.busbooking.Auth.Domain.UsersEntity;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "bus_id")
    private BusEntity busEntity;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UsersEntity usersEntity;

}
