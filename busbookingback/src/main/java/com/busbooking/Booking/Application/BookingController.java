package com.busbooking.Booking.Application;


import com.busbooking.Booking.Domain.BookingEntity;
import com.busbooking.Booking.Domain.BookingMapper;
import com.busbooking.Booking.Application.Dto.BookingFormInputDto;
import com.busbooking.Booking.Domain.BookingService;
import com.busbooking.ErrorHandling.SuccessDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("empresa/v0/bookings")
public record BookingController(BookingService bookingService) {
    @PostMapping
    public ResponseEntity<SuccessDto> createBooking(@RequestBody @Valid BookingFormInputDto bookingFormInputDto, @RequestHeader("Authorization") String auth){
        BookingEntity bookingEntity = BookingMapper.MAP.bookingInDtoToEntity(bookingFormInputDto);
        bookingService.createBooking(bookingEntity);
        return SuccessDto.send("Your booking has been registered successfully");
    }
}
