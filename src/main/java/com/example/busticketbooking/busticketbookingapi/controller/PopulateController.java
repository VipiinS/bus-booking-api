package com.example.busticketbooking.busticketbookingapi.controller;

import com.example.busticketbooking.busticketbookingapi.dto.request.BusDto;
import com.example.busticketbooking.busticketbookingapi.dto.request.RouteDto;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.PopulateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class PopulateController {

    @Autowired
    private PopulateService populateService;

    @GetMapping("/")
    public ResponseEntity<String> AdminEndpoint() {
        return ResponseEntity.ok("Admin Endpoint Accessed");
    }

    @PostMapping("/add-route")
    public ResponseEntity<?> populateRoutes(@RequestBody RouteDto routeData) {
        try {
            Long routeId = populateService.populateRoutes(routeData);
            return ResponseEntity.ok("Route from "+ routeData.getPickup()+" to "+ routeData.getDestination()+" successfully with route id: " + routeId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-bus")
    public ResponseEntity<?> populateBus(@RequestBody BusDto busData){
        try {
            populateService.populateBus(busData);
            return ResponseEntity.ok("Bus "+busData.getRegistrationNumber()+ " added to "+ busData.getRouteId()+" successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
