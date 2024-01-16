package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.BusDto;
import com.example.busticketbooking.busticketbookingapi.dto.RouteDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;

public interface PopulateService {
    void populateRoutes(RouteDto routeData);

    void populateBus(BusDto busData);
    void populateSeats(Bus bus);
}
