package com.example.busticketbooking.busticketbookingapi.controller;

import com.example.busticketbooking.busticketbookingapi.dto.BusDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/searchByRoute")
    public ResponseEntity<?> findBusByRoute(@RequestParam("origin") String origin,
                                                       @RequestParam("destination") String destination){
        try{
            List<Bus> buses = busService.findBusByRoute(origin,destination);
            List<BusDto> busDtos = convertBusToBusDto(buses);
            return ResponseEntity.ok(busDtos);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private List<BusDto> convertBusToBusDto(List<Bus> buses) {
        List<BusDto> busDtos = new ArrayList<>();

        for(Bus bus : buses){
            BusDto busDto = new BusDto();

            busDto.setRegistrationNumber(bus.getRegistrationNumber());
            busDto.setFare(bus.getFare());
            busDto.setType(bus.getType());
            busDto.setCapacity(bus.getCapacity());
            busDtos.add(busDto);
        }
        return busDtos;
    }
}
