package com.example.busticketbooking.busticketbookingapi.controller;

import com.example.busticketbooking.busticketbookingapi.dto.request.BusDto;
import com.example.busticketbooking.busticketbookingapi.dto.response.SeatDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.BusService;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/bus")
public class BusController {

    @Autowired
    private BusService busService;
    @Autowired
    private SeatService seatService;

    @GetMapping("/")
    public ResponseEntity<String> openForUser(){
        return ResponseEntity.ok("OpenForUser endpoint accessed");
    }

//    @GetMapping("/searchByRoute")
//    public ResponseEntity<?> findBusByRoute(@RequestParam("origin") String origin,
//                                                       @RequestParam("destination") String destination){
//        try{
//            List<Bus> buses = busService.findBusByRoute(origin,destination);
//            List<BusDto> busDtos = convertBusToBusDto(buses);
//            return ResponseEntity.ok(busDtos);
//        }catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @GetMapping("/{busId}/seats")
    public ResponseEntity<?> getSeatsByBusId(@PathVariable Long busId){
        try {
            List<SeatDto> seatDtos = seatService.getSeatsByBusId(busId);
            return ResponseEntity.ok(seatDtos);
        }catch (Exception e){
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
