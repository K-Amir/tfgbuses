package com.busbooking.Booking.Application;


import com.busbooking.Booking.Domain.BookingEntity;
import com.busbooking.Booking.Domain.BookingMapper;
import com.busbooking.Booking.Application.Dto.BookingFormInputDto;
import com.busbooking.Booking.Domain.BookingService;
import com.busbooking.Booking.Infrastructure.StripeService;
import com.busbooking.ErrorHandling.SuccessDto;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("empresa/v0/bookings")
public record BookingController(BookingService bookingService, StripeService stripeService) {
    @PostMapping
    public ResponseEntity<SuccessDto> createBooking(@RequestBody @Valid BookingFormInputDto bookingFormInputDto) {

        BookingEntity bookingEntity = BookingMapper.MAP.bookingInDtoToEntity(bookingFormInputDto);

        bookingService.createBooking(bookingEntity, bookingFormInputDto);

        return SuccessDto.send("Your booking has been registered successfully");
    }

    @PostMapping("buy")
    public ResponseEntity<?> buyItem(@RequestParam("token") String token, @RequestParam("amount") String amount, @RequestParam("email") String email) throws Exception {

        // amount goes in cents
        String chargeId = stripeService.createCharge(email, token, Integer.parseInt(amount));

        SessionCreateParams params = SessionCreateParams.builder().build();

        return SuccessDto.send("Charged successfully id" + chargeId);
    }

}
