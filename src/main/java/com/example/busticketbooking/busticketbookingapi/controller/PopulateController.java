package com.example.busticketbooking.busticketbookingapi.controller;

import com.example.busticketbooking.busticketbookingapi.dto.BusDto;
import com.example.busticketbooking.busticketbookingapi.dto.RouteDto;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.PopulateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class PopulateController {

    @Autowired
    private PopulateService populateService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> AdminEndpoint() {
        return ResponseEntity.ok("Admin Endpoint Accessed");
    }

    @PostMapping("/routes")
    public ResponseEntity<?> populateRoutes(@RequestBody RouteDto routeData) {
        try {
            populateService.populateRoutes(routeData);
            return ResponseEntity.ok("Route from "+ routeData.getOrigin()+"to "+ routeData.getDestination()+" successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/bus")
    public ResponseEntity<?> populateBus(@RequestBody BusDto busData){
        try {
            populateService.populateBus(busData);
            return ResponseEntity.ok("Bus "+busData.getRegistrationNumber()+ " added to "+ busData.getRouteId()+" successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
