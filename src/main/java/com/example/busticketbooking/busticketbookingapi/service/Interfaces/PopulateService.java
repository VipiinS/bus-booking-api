package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.request.BusDto;
import com.example.busticketbooking.busticketbookingapi.dto.request.RouteDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;

public interface PopulateService {
    Long populateRoutes(RouteDto routeData);

    void populateBus(BusDto busData);
    void populateSeats(Bus bus);
}
