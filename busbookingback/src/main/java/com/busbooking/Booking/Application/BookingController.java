package com.busbooking.Booking.Application;


import com.busbooking.Auth.Domain.AuthService;
import com.busbooking.Auth.Domain.UsersEntity;
import com.busbooking.Auth.Infrastructure.JwtUtil;
import com.busbooking.Booking.Application.Dto.BookingsUserDto;
import com.busbooking.Booking.Application.Dto.BuyingInputDto;
import com.busbooking.Booking.Domain.BookingEntity;
import com.busbooking.Booking.Domain.BookingMapper;
import com.busbooking.Booking.Application.Dto.BookingFormInputDto;
import com.busbooking.Booking.Domain.BookingService;
import com.busbooking.Booking.Infrastructure.StripeService;
import com.busbooking.Bus.Domain.BusEntity;
import com.busbooking.Bus.Domain.BusService;
import com.busbooking.ErrorHandling.SuccessDto;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("empresa/v0/bookings")
public record BookingController(
        BookingService bookingService,
        JwtUtil jwtUtil
) {


    @PostMapping("buy")
    public ResponseEntity<?> buyItem(@RequestBody BuyingInputDto buyingInputDto) throws Exception {
        try {

            bookingService.createBooking(buyingInputDto);

        } catch (Exception ex) {
            throw ex;
        }

        return SuccessDto.send("Charged successfully id");
    }

    @GetMapping("/{jwt}")
    public ResponseEntity<?> getUserBoughtedBookings(@PathVariable String jwt) {
        String userEmail = jwtUtil.getSubject(jwt);

        List<BookingEntity> bookingEntities = bookingService.findBookingByEmails(userEmail);

        List<BookingsUserDto> bookings = new ArrayList<>();

        bookingEntities.forEach(x -> {

            var booking = BookingMapper.MAP.bookingEntityToUserOutput(x);

            booking.setJwtQr(x.getUsersEntity().getEmail());

            bookings.add(booking);
        });




        return ResponseEntity.ok(bookings);
    }


}
