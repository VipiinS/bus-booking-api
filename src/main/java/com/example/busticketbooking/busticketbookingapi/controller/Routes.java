package com.example.busticketbooking.busticketbookingapi.controller;

import com.example.busticketbooking.busticketbookingapi.dao.RouteDto;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.PopulateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class Routes {

    @Autowired
    private PopulateService populateService;

    @PostMapping("/populate")
    public ResponseEntity<?> populateRoutes(@RequestBody RouteDto routeData) {
        try {
            populateService.populateRoutes(routeData);
            return ResponseEntity.ok("Routes populated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
