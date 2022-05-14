package com.busbooking.Incidence.Application;

import com.busbooking.Booking.Domain.BookingEntity;
import com.busbooking.Booking.Domain.BookingService;
import com.busbooking.Mail.Domain.MailService;
import com.busbooking.ErrorHandling.SuccessDto;
import com.busbooking.Incidence.Application.Dto.IncidenceInputDto;
import com.busbooking.Incidence.Application.Dto.IncidenceOutputDto;
import com.busbooking.Incidence.Domain.IncidenceEntity;
import com.busbooking.Incidence.Domain.IncidenceMapper;
import com.busbooking.Incidence.Domain.IncidenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("empresa/v0/incidences")
@AllArgsConstructor
public class IncidenceController {
    private final IncidenceService incidenceService;
    private final BookingService bookingService;
    private final MailService mailService;

    @PostMapping
    @Transactional
    public ResponseEntity<SuccessDto> createIncidence(@RequestBody @Valid IncidenceInputDto incidenceInputDto) throws InterruptedException {

        IncidenceEntity incidenceEntity = IncidenceMapper.MAPPER.incidenceInputToEntity(incidenceInputDto);

        incidenceService.save(incidenceEntity, incidenceInputDto.getBus_id());

        mailService.sendCancellationMails(bookingService.findBookingEntitiesByBusEntity_Id(incidenceInputDto.getBus_id()), incidenceEntity);

        bookingService.deleteBookingsByBusEntityId(incidenceInputDto.getBus_id());

        return SuccessDto.send("Incidence registered successfully :(");


    }

    @GetMapping("{id}")
    public ResponseEntity<IncidenceOutputDto> findIncidenceById(@PathVariable int id) {
        IncidenceEntity incidenceEntity = incidenceService.findById(id);
        IncidenceOutputDto incidenceOutputDto = IncidenceMapper.MAPPER.incidenceToOutput(incidenceEntity);
        return ResponseEntity.ok(incidenceOutputDto);
    }

    @GetMapping()
    public ResponseEntity<List<IncidenceOutputDto>> findAllIncidences() {
        List<IncidenceEntity> incidenceEntityList = incidenceService.findAll();
        List<IncidenceOutputDto> output = IncidenceMapper.MAPPER.incidenceToOutputList(incidenceEntityList);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessDto> deleteIncidenceById(@PathVariable int id) {
        incidenceService.deleteById(id);
        return SuccessDto.send("Incidence with id " + id + " deleted successfully");
    }



}
