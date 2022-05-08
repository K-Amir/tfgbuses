package com.busbooking.Bus.Application;


import com.busbooking.Bus.Application.Dto.BusInputDto;
import com.busbooking.Bus.Application.Dto.BusOutputDto;
import com.busbooking.Bus.Domain.BusEntity;
import com.busbooking.Bus.Domain.BusMapper;
import com.busbooking.Bus.Domain.BusService;
import com.busbooking.ErrorHandling.SuccessDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("empresa/v0/buses")
public record BusController(BusService busService) {


    @GetMapping("/available")
    public ResponseEntity<?> findAvailableBuses(@RequestParam HashMap<String, Object> params) {
        List<BusEntity> busEntityList = busService.getAvailableBookings(params);
        List<BusOutputDto> busOutputDtoList = BusMapper.MAP.inputListToOutput(busEntityList);
        return ResponseEntity.ok(busOutputDtoList);
    }

    @PostMapping()
    public ResponseEntity<SuccessDto> saveBus(@RequestBody @Valid BusInputDto busInputDto) {
        BusEntity busEntity = BusMapper.MAP.inputDtoToEntity(busInputDto);
        busService.createBus(busEntity);
        return SuccessDto.send("Bus created successfully :) !");
    }

    @GetMapping
    public ResponseEntity<List<BusOutputDto>> findAllBuses() {
        List<BusEntity> busEntityList = busService.findAllBuses();
        List<BusOutputDto> busOutputDtoList = BusMapper.MAP.inputListToOutput(busEntityList);
        return ResponseEntity.ok(busOutputDtoList);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessDto> deleteBusById(@PathVariable int id) {
        busService.deleteBusById(id);
        return SuccessDto.send("Bus with id " + id + " deleted successfully");
    }

    @GetMapping("{id}")
    public ResponseEntity<BusOutputDto> findBusById(@PathVariable int id) {
        BusEntity busEntity = busService.findBusById(id);
        BusOutputDto busOutputDto = BusMapper.MAP.entityToOutput(busEntity);
        return ResponseEntity.ok(busOutputDto);
    }

}
